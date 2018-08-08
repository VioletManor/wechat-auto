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
import java.util.ArrayList;
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
            System.out.println("正在监听");
            try {
                Map<String, String> syncCheck = JSON.parseObject(syncCheck(wechatInfo).split("=")[1], Map.class);
                String selector = syncCheck.get("selector");
                System.out.println("retcode ==> " + syncCheck.get("retcode") + " || " + "selector ==> " + selector);
                if (selector.equals("2")) {
                    System.out.printf("收到消息： "+ getTextMsg(wechatInfo));
                    continue;
                }
                Thread.sleep(26000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    public String syncCheck(WechatInfo wechatInfo) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("r", System.currentTimeMillis() + ""));
        params.add(new BasicNameValuePair("_", System.currentTimeMillis() + ""));
        params.add(new BasicNameValuePair("skey", wechatInfo.getSkey()));
        params.add(new BasicNameValuePair("sid", wechatInfo.getWxsid()));
        params.add(new BasicNameValuePair("uin", wechatInfo.getWxuin()));
        params.add(new BasicNameValuePair("deviceid", wechatInfo.getDeviceId()));
        params.add(new BasicNameValuePair("synckey", wechatInfo.getSyncKeyStr()));
        /*StringBuffer url = new StringBuffer(Constants.SYNC_CHECK_URL);
        url.append("?r=").append(System.currentTimeMillis())
                .append("&_=").append(System.currentTimeMillis())
                .append("&skey=").append(wechatInfo.getSkey())
                .append("&sid=").append(wechatInfo.getWxsid())
                .append("&uin=").append(wechatInfo.getWxuin())
                .append("&deviceid=").append(wechatInfo.getDeviceId())
                .append("&synckey=").append(wechatInfo.getSyncKeyStr());*/
        HttpResponse httpResponse = HttpClientHelper.doPost(Constants.SYNC_CHECK_URL, params, wechatInfo.getCookie());
        return httpResponse.getContent();
    }

    public String getTextMsg(WechatInfo wechatInfo) {
        JSONObject payLoad = new JSONObject();
        payLoad.put("BaseRequest", wechatInfo.getPayLoad());
        payLoad.put("SyncKey", wechatInfo.getSyncKey());
        payLoad.put("rr", System.currentTimeMillis());

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", wechatInfo.getWxsid()));
        params.add(new BasicNameValuePair("skey", wechatInfo.getSkey()));
        params.add(new BasicNameValuePair("lang", "zh_CN"));
        params.add(new BasicNameValuePair("pass_ticket", wechatInfo.getPassicket()));

        HttpResponse httpResponse = HttpClientHelper.doPost(Constants.WEB_WX_SYNC_URL, params, wechatInfo.getCookie(), payLoad.toJSONString(), "application/json");
        return httpResponse.getContent();
    }
}
