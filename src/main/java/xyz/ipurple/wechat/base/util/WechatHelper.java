package xyz.ipurple.wechat.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import xyz.ipurple.wechat.base.core.WechatInfo;
import xyz.ipurple.wechat.base.core.init.ContactEntity;
import xyz.ipurple.wechat.base.core.init.WechatInitEntity;
import xyz.ipurple.wechat.base.core.sync.key.KeyEntity;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: WechatHelper
 * @Description: //TODO
 * @Author: zcy
 * @Date: 2018/8/6 10:32
 * @Version: 1.0
 */
public class WechatHelper {
//    private static final ThreadLocal<QRCodeWindow> QR_CODE_WINDOW = new ThreadLocal<QRCodeWindow>();
    private static QRCodeWindow QR_CODE_WINDOW = null;

    /**
     * 获取二维码登陆uuid
     * @return
     */
    public static String getQrLoginUUID() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appid", "wx782c26e4c19acffb"));
        params.add(new BasicNameValuePair("redirect_uri", "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxnewloginpage"));
        params.add(new BasicNameValuePair("fun", "new"));
        params.add(new BasicNameValuePair("lang", "zh_CN"));
        params.add(new BasicNameValuePair("_", System.currentTimeMillis()+""));
        HttpResponse httpResponse = HttpClientHelper.doPost(Constants.JS_LOGIN_URL, params);
        String result = httpResponse.getContent();
        if (StringUtils.isBlank(result)) {
            throw new RuntimeException("获取UUID失败");
        }
        String code = MatcheHelper.matches("window.QRLogin.code = (\\d+);", result);
        if (null == code || !code.equals("200")) {
            throw new RuntimeException("获取uuid失败," + code );
        }
        return MatcheHelper.matches("window.QRLogin.uuid = \"(.*)\";", result);
    }

    public static String getQrCode(String uuid) {
        if (StringUtils.isBlank(uuid)) {
            throw new RuntimeException("uuid不存在");
        }
        String url = Constants.LOGIN_QRCODE + uuid;
        String path = Constants.QRCODE_TEMP_DIR;
        String fileName = uuid + ".jpg";
        HttpClientHelper.doPostFile(url, null, Constants.QRCODE_TEMP_DIR,  fileName);
        return path + File.separator + fileName;
    }

    public static void showQrCode(final String path) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    QRCodeWindow qrCodeWindow = new QRCodeWindow(path);
                    QR_CODE_WINDOW = qrCodeWindow;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void closeQrCode() {
        QR_CODE_WINDOW.dispose();
    }

    public static String waitLogin(int tip, String uuid) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("loginicon", "true"));
        params.add(new BasicNameValuePair("uuid", uuid));
        params.add(new BasicNameValuePair("tip", tip + ""));
//        params.add(new BasicNameValuePair("r", ""));
        params.add(new BasicNameValuePair("_", System.currentTimeMillis() + ""));
        HttpResponse httpResponse = HttpClientHelper.doPost(Constants.WAIT_LOGIN, params);
        return httpResponse.getContent();
    }

    /**
     * 扫码后跳转链接并初始化基础信息
     * @param res
     */
    public static WechatInfo redirect(String res) {
        String redirectUri = MatcheHelper.matches("window.redirect_uri=\"(.*)\"", res);
        HttpResponse httpResponse = HttpClientHelper.doPost(redirectUri+"&fun=new", null);
        String content = httpResponse.getContent();

        String skey = MatcheHelper.matches("<skey>(.*)</skey>", content);
        String wxsid = MatcheHelper.matches("<wxsid>(.*)</wxsid>", content);
        String wxuin = MatcheHelper.matches("<wxuin>(.*)</wxuin>", content);
        String passTicket = MatcheHelper.matches("<pass_ticket>(.*)</pass_ticket>", content);

        WechatInfo info = new WechatInfo();
        info.setCookie(httpResponse.getCookie());
        info.setPassicket(passTicket);
        info.setSkey(skey);
        info.setWxsid(wxsid);
        info.setWxuin(wxuin);

        JSONObject json = new JSONObject();
        json.put("DeviceID", info.getDeviceId());
        json.put("Sid", info.getWxsid());
        json.put("Skey", info.getSkey());
        json.put("Uin", info.getWxuin());
        info.setPayLoad(json);
        return info;
    }

    public static void init(WechatInfo wechatInfo) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("lang", "zh_CN"));
        params.add(new BasicNameValuePair("pass_ticket", wechatInfo.getPassicket()));
        params.add(new BasicNameValuePair("r", System.currentTimeMillis()+""));

        JSONObject payLoad = new JSONObject();
        payLoad.put("BaseRequest", wechatInfo.getPayLoad());
        HttpResponse httpResponse = HttpClientHelper.doPost(Constants.INIT_URL, params, wechatInfo.getCookie(), payLoad.toJSONString(), "application/json");
        String content = httpResponse.getContent();
        WechatInitEntity wechatInitEntity = JSON.parseObject(content, WechatInitEntity.class);
        //获取登陆人信息
        wechatInfo.setUser(wechatInitEntity.getUser());

        //获取sync 字符串型
        StringBuffer sb = new StringBuffer();
        Iterator<KeyEntity> syncKeyIt = wechatInitEntity.getSyncKey().getList().iterator();
        while (syncKeyIt.hasNext()) {
            KeyEntity next = syncKeyIt.next();
            sb.append("|")
                    .append(next.getKey())
                    .append("_")
                    .append(next.getVal())
                    ;
        }
        wechatInfo.setSyncKeyStr(sb.substring(1));
        wechatInfo.setSyncKey(wechatInitEntity.getSyncKey());

        Iterator<ContactEntity> iterator = wechatInitEntity.getContactList().iterator();
        while (iterator.hasNext()) {
            ContactEntity next = iterator.next();
            System.out.println(next.getUserName() + "  ||  " + next.getNickName());
        }
    }

    public static void statusNotify(WechatInfo wechatInfo) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("lang", "zh_CN"));
        params.add(new BasicNameValuePair("pass_ticket", wechatInfo.getPassicket()));

        JSONObject payLoad = new JSONObject();
        payLoad.put("BaseRequest", wechatInfo.getPayLoad());
        payLoad.put("ClientMsgId", System.currentTimeMillis());
        payLoad.put("Code", 3);
        payLoad.put("FromUserName", wechatInfo.getUser().getUserName());
        payLoad.put("ToUserName", wechatInfo.getUser().getUserName());

        HttpResponse httpResponse = HttpClientHelper.doPost(Constants.STATUS_NOTIFY_URL, params, wechatInfo.getCookie(), payLoad.toJSONString(), "application/json");
        JSONObject notifyResponse = JSON.parseObject(httpResponse.getContent());
        if (!((JSONObject) notifyResponse.get("BaseResponse")).get("Ret").equals(0)) {
            throw new RuntimeException("消息通知开启失败");
        }
    }

    public static String getContact(WechatInfo wechatInfo) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("skey", wechatInfo.getSkey()));
        params.add(new BasicNameValuePair("r", System.currentTimeMillis()+""));
        params.add(new BasicNameValuePair("pass_ticket", wechatInfo.getPassicket()));

        HttpResponse httpResponse = HttpClientHelper.doPost(Constants.GET_CONTACT_URL, params, wechatInfo.getCookie());
        return httpResponse.getContent();
    }
}
