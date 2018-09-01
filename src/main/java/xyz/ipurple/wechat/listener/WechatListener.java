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
        //选择线路
        WechatHelper.chooseSyncLine(wechatInfo);
        while (true) {
            try {
                logger.info("wechat#" + Thread.currentThread().getId() + "--正在监听:");
                int[] result = WechatHelper.syncCheck(wechatInfo);
                if (result[0] == 1100 || result[0] == 1101) {
                    logger.info("wechat#" + Thread.currentThread().getId() + "--用户微信退出");
                    break;
                }
                if (2 == result[1]) {  //有消息
                    SyncEntity syncEntity = WechatHelper.getTextMsg(wechatInfo);
                    Iterator<MsgEntity> msgIt = syncEntity.getAddMsgList().iterator();
                    while (msgIt.hasNext()) {
                        MsgEntity next = msgIt.next();
                        if (next.getMsgType() == WechatMsgConstants.REVOKE_MSG) {
                            //撤回消息处理
                            MSG_HANDLER.revokeHandler(next);
                            continue;
                        } else if (next.getMsgType() == 51) {
                            logger.info("wechat#" + Thread.currentThread().getId() + "--msgType:{},此消息类型不做处理", next.getMsgType());
                        } else {
                            UserContext.getMsgThreadLocal().put(next.getMsgId(), next);
                            UserContext.clearExpireMsg();
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
}
