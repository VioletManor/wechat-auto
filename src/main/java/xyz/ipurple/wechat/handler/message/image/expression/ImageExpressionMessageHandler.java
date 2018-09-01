package xyz.ipurple.wechat.handler.message.image.expression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ipurple.wechat.base.constants.Constants;
import xyz.ipurple.wechat.base.constants.WeChatContactConstants;
import xyz.ipurple.wechat.base.core.revoke.RevokeMsgInfo;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;
import xyz.ipurple.wechat.base.util.WechatHelper;
import xyz.ipurple.wechat.handler.message.IMessageHandler;

/**
 * @ClassName: ImageExpressionMessageHandler
 * @Description: 图片表情消息
 * @Author: zcy
 * @Date: 2018/8/31 11:39
 * @Version: 1.0
 */
public class ImageExpressionMessageHandler extends ImageExpressionAbstractMessageHandler implements IMessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(ImageExpressionMessageHandler.class);

    @Override
    public void receiveHandler(MsgEntity msgEntity) {
        if (Constants.RECEIVE_MSG_FLAG) {
            logger.info("收到图片表情");
        }
    }

    @Override
    public void revokeHandler(MsgEntity msgEntity) {
        RevokeMsgInfo revokeMsgInfo = getRevokeMsgInfo(msgEntity);
        StringBuilder revokeMsgContent = getPreContentForRevokeMsg(revokeMsgInfo).append("图片表情");
        WechatHelper.sendTextMsg(revokeMsgContent.toString(), WeChatContactConstants.FILE_HELPER);
        WechatHelper.sendEmoticonMsg(revokeMsgInfo.getContent(), WeChatContactConstants.FILE_HELPER);
    }
}
