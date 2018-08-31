package xyz.ipurple.wechat.handler.message.txt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ipurple.wechat.base.constants.WeChatContactConstants;
import xyz.ipurple.wechat.base.core.revoke.RevokeMsgInfo;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;
import xyz.ipurple.wechat.base.util.WechatHelper;
import xyz.ipurple.wechat.handler.message.IMessageHandler;

/**
 * @ClassName: TxtMessageHandler
 * @Description: 文本消息处理
 * @Author: zcy
 * @Date: 2018/8/31 11:27
 * @Version: 1.0
 */
public class TxtMessageHandler extends TxtAbstractMessageHandler implements IMessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(TxtMessageHandler.class);

    @Override
    public void receiveHandler(MsgEntity msgEntity) {
        logger.info("收到普通消息:" + msgEntity.getContent());
    }

    @Override
    public void revokeHandler(MsgEntity msgEntity) {
        RevokeMsgInfo revokeMsgInfo = getRevokeMsgInfo(msgEntity);
        StringBuilder revokeMsgContent = this.getPreContentForRevokeMsg(revokeMsgInfo).append(revokeMsgInfo.getContent());
        WechatHelper.sendTextMsg(revokeMsgContent.toString(), WeChatContactConstants.FILE_HELPER);
    }
}
