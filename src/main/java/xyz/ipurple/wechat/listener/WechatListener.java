package xyz.ipurple.wechat.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import sun.rmi.runtime.Log;
import xyz.ipurple.wechat.base.core.WechatInfo;
import xyz.ipurple.wechat.base.util.Constants;
import xyz.ipurple.wechat.base.util.HttpClientHelper;
import xyz.ipurple.wechat.base.util.HttpResponse;
import xyz.ipurple.wechat.login.core.Login;

import javax.sound.midi.Soundbank;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: WechatListener
 * @Description: //TODO
 * @Author: zcy
 * @Date: 2018/8/8 12:43
 * @Version: 1.0
 */
public class WechatListener {

    public void listen() {
        WechatInfo wechatInfo = Login.WECHAT_INFO_THREAD_LOCAL.get();
        while (true) {
            try {
                wechatInfo.setDeviceId("e" + System.currentTimeMillis());
                Map<String, String> syncCheck = JSON.parseObject(syncCheck(wechatInfo).split("=")[1], Map.class);
                String selector = syncCheck.get("selector");
                String retcode = syncCheck.get("retcode");
                if (!retcode.equals("0")) {
                    throw new RuntimeException("sync check 失败");
                }
                if (selector.equals("2")) {
                    System.out.println("retcode ==> " + syncCheck.get("retcode") + " || " + "selector ==> " + selector);
                    System.out.printf("收到消息： " + getTextMsg(wechatInfo));
                    continue;
                }
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public String syncCheck(WechatInfo wechatInfo) throws UnsupportedEncodingException {
        StringBuffer url = new StringBuffer();
        url.append("?r=").append(System.currentTimeMillis())
                .append("&skey=").append(URLEncoder.encode(wechatInfo.getSkey()))
                .append("&sid=").append(URLEncoder.encode(wechatInfo.getWxsid()))
                .append("&uin=").append(URLEncoder.encode(wechatInfo.getWxuin()))
                .append("&deviceid=").append(URLEncoder.encode(wechatInfo.getDeviceId()))
                .append("&synckey=").append(URLEncoder.encode(wechatInfo.getSyncKeyStr()))
                .append("&_=").append(System.currentTimeMillis());

        HttpResponse httpResponse = HttpClientHelper.doGet(Constants.SYNC_CHECK_URL + url.toString(), wechatInfo.getCookie());
        System.out.println(Constants.SYNC_CHECK_URL + url.toString());
        System.out.println(httpResponse.getContent());
        return httpResponse.getContent();
    }

    public String getTextMsg(WechatInfo wechatInfo) {
        JSONObject payLoad = new JSONObject();
        payLoad.put("BaseRequest", wechatInfo.getPayLoad());
        payLoad.put("SyncKey", wechatInfo.getSyncKey());
        payLoad.put("rr", ~new Date().hashCode());

        /*List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", wechatInfo.getWxsid()));
        params.add(new BasicNameValuePair("skey", wechatInfo.getSkey()));
        params.add(new BasicNameValuePair("lang", "zh_CN"));
        params.add(new BasicNameValuePair("pass_ticket", wechatInfo.getPassicket()));*/
        StringBuffer url = new StringBuffer();
        url.append("?sid=").append(URLEncoder.encode(wechatInfo.getWxsid()))
                .append("&skey=").append(URLEncoder.encode(wechatInfo.getSkey()))
                .append("&lang=").append("zh_CN")
                .append("&pass_ticket=").append(URLEncoder.encode(wechatInfo.getPassicket()));

        HttpResponse httpResponse = HttpClientHelper.doPost(Constants.WEB_WX_SYNC_URL + url.toString(), null, wechatInfo.getCookie(), payLoad.toJSONString(), "application/json");
        return httpResponse.getContent();
    }
}
