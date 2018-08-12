package xyz.ipurple.wechat.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ipurple.wechat.base.core.WechatInfo;
import xyz.ipurple.wechat.base.core.sync.SyncEntity;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;
import xyz.ipurple.wechat.base.util.Constants;
import xyz.ipurple.wechat.base.util.HttpClientHelper;
import xyz.ipurple.wechat.base.util.HttpResponse;
import xyz.ipurple.wechat.base.util.WechatHelper;
import xyz.ipurple.wechat.login.core.Login;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName: WechatListener
 * @Description: //TODO
 * @Author: zcy
 * @Date: 2018/8/8 12:43
 * @Version: 1.0
 */
public class WechatListener {
    private static final Logger logger = LoggerFactory.getLogger(WechatListener.class);

    public void listen() {
        WechatInfo wechatInfo = Login.WECHAT_INFO_THREAD_LOCAL.get();
        while (true) {
            try {
                logger.info("正在监听消息:");
                System.out.println("正在监听消息: ");
                wechatInfo.setDeviceId("e" + System.currentTimeMillis());
                Map<String, String> syncCheck = JSON.parseObject(syncCheck(wechatInfo).split("=")[1], Map.class);
                String selector = syncCheck.get("selector");
                String retcode = syncCheck.get("retcode");
                if (!retcode.equals("0")) {
                    throw new RuntimeException("sync check 失败");
                }
                if (selector.equals("0")) {
                    System.out.printf("未检测到新消息");
                }
                if (selector.equals("2")) {
                    SyncEntity syncEntity = JSON.parseObject(getTextMsg(wechatInfo), SyncEntity.class);
                    wechatInfo.setSyncKey(syncEntity.getSyncKey());
                    wechatInfo.setSyncKeyStr(WechatHelper.createSyncKey(syncEntity.getSyncKey()));
                    int ret = syncEntity.getBaseResponse().getRet();
                    if (ret != 0) {
                        throw new RuntimeException("获取最新消息失败");
                    } else {
                        Iterator<MsgEntity> msgIt = syncEntity.getAddMsgList().iterator();
                        while (msgIt.hasNext()) {
                            MsgEntity next = msgIt.next();
                            System.out.println(next.getContent());
                            logger.info(next.getContent());
                        }
                    }
                    continue;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public String syncCheck(WechatInfo wechatInfo) {
        StringBuffer url = new StringBuffer();
        url.append("?r=").append(System.currentTimeMillis())
                .append("&skey=").append(URLEncoder.encode(wechatInfo.getSkey()))
                .append("&sid=").append(URLEncoder.encode(wechatInfo.getWxsid()))
                .append("&uin=").append(URLEncoder.encode(wechatInfo.getWxuin()))
                .append("&deviceid=").append(URLEncoder.encode(wechatInfo.getDeviceId()))
                .append("&synckey=").append(URLEncoder.encode(wechatInfo.getSyncKeyStr()))
                .append("&_=").append(System.currentTimeMillis());

        HttpResponse httpResponse = HttpClientHelper.build(Constants.SYNC_CHECK_URL + url.toString(), wechatInfo.getCookie()).doGet();
        return httpResponse.getContent();
    }

    public String getTextMsg(WechatInfo wechatInfo) throws UnsupportedEncodingException {
        JSONObject payLoad = new JSONObject();
        payLoad.put("BaseRequest", wechatInfo.getBaseRequest());
        payLoad.put("SyncKey", wechatInfo.getSyncKey());
        payLoad.put("rr", ~new Date().getTime());

        StringBuffer url = new StringBuffer(Constants.SYNC_URL);
        url.append("?sid=").append(URLEncoder.encode(wechatInfo.getWxsid(),"utf-8"))
                .append("&skey=").append(URLEncoder.encode(wechatInfo.getSkey(),"utf-8"))
                .append("&lang=").append("zh_CN")
                .append("&pass_ticket=").append(wechatInfo.getPassicket());

        HttpResponse httpResponse = HttpClientHelper.build(url.toString(), wechatInfo.getCookie()).setPayLoad(payLoad.toJSONString()).doPost();
        return httpResponse.getContent();
    }
}
