package xyz.ipurple.wechat.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ipurple.wechat.base.constants.Constants;
import xyz.ipurple.wechat.base.constants.WechatMsgConstants;
import xyz.ipurple.wechat.base.core.WechatInfo;
import xyz.ipurple.wechat.base.core.sync.SyncEntity;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;
import xyz.ipurple.wechat.base.util.HttpClientHelper;
import xyz.ipurple.wechat.base.util.HttpResponse;
import xyz.ipurple.wechat.base.util.WechatHelper;
import xyz.ipurple.wechat.handler.WechatMsgHandler;
import xyz.ipurple.wechat.handler.message.IMessageHandler;
import xyz.ipurple.wechat.login.UserContext;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;

/**
 * @ClassName: WechatListener
 * @Description:
 * @Author: zcy
 * @Date: 2018/8/8 12:43
 * @Version: 1.0
 */
public class WechatListener {
    private static final Logger logger = LoggerFactory.getLogger(WechatListener.class);
    private static final IMessageHandler MSG_HANDLER = new WechatMsgHandler();

    public void listen() {
        WechatInfo wechatInfo = UserContext.getWechatInfoThreadLocal();
        while (true) {
            try {
                logger.info("正在监听:");
                String selector = syncCheck(wechatInfo);
                if (selector.equals("2")) {  //有消息
                    SyncEntity syncEntity = getTextMsg(wechatInfo);
                    Iterator<MsgEntity> msgIt = syncEntity.getAddMsgList().iterator();
                    while (msgIt.hasNext()) {
                        MsgEntity next = msgIt.next();
                        if (next.getMsgType() == WechatMsgConstants.REVOKE_MSG) {
                            //撤回消息处理
                            MSG_HANDLER.revokeHandler(next);
                            continue;
                        } else {
                            UserContext.getMsgThreadLocal().put(next.getMsgId(), next);
                            //接收消息处理
                            MSG_HANDLER.receiveHandler(next);
                        }
                    }
                    Thread.sleep(2000);
                }
            } catch (UnsupportedEncodingException e) {
                logger.error("UnsupportedEncodingException", e);
            } catch (NullPointerException e) {
                logger.error("NullPointerException", e);
            } catch (InterruptedException e) {
                logger.error("InterruptedException", e);
            }
        }
    }

    /**
     * 检查是否有最新消息
     * @param wechatInfo
     * @return
     */
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
        JSONObject syncCheck = JSON.parseObject(httpResponse.getContent().split("=")[1]);
        String retcode = syncCheck.get("retcode").toString();
        if (!retcode.equals("0")) {
            throw new RuntimeException("sync check 失败");
        }
        return syncCheck.get("selector").toString();
    }

    /**
     * 获取最新消息
     * @param wechatInfo
     * @return
     * @throws UnsupportedEncodingException
     */
    public SyncEntity getTextMsg(WechatInfo wechatInfo) throws UnsupportedEncodingException {
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
        SyncEntity syncEntity = JSON.parseObject(httpResponse.getContent(), SyncEntity.class);
        //更新synckey
        wechatInfo.setSyncKey(syncEntity.getSyncKey());
        wechatInfo.setSyncKeyStr(WechatHelper.createSyncKey(syncEntity.getSyncKey()));
        int ret = syncEntity.getBaseResponse().getRet();
        if (ret != 0) {
            throw new RuntimeException("获取最新消息失败");
        }
        return syncEntity;
    }
}
