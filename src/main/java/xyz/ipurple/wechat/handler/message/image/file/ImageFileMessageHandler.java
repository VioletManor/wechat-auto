package xyz.ipurple.wechat.handler.message.image.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ipurple.wechat.base.constants.Constants;
import xyz.ipurple.wechat.base.constants.WeChatContactConstants;
import xyz.ipurple.wechat.base.core.revoke.RevokeMsgInfo;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;
import xyz.ipurple.wechat.base.util.WechatHelper;
import xyz.ipurple.wechat.handler.message.IMessageHandler;

/**
 * @ClassName: ImageFileMessageHandler
 * @Description: 图片文件消息处理
 * @Author: zcy
 * @Date: 2018/8/31 11:36
 * @Version: 1.0
 */
public class ImageFileMessageHandler extends ImageFileAbstractMessageHandler implements IMessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(ImageFileMessageHandler.class);

    @Override
    public void receiveHandler(MsgEntity msgEntity) {
        if (Constants.RECEIVE_MSG_FLAG) {
            logger.info("收到图片文件");
        }
    }

    @Override
    public void revokeHandler(MsgEntity msgEntity) {
        RevokeMsgInfo revokeMsgInfo = getRevokeMsgInfo(msgEntity);
        StringBuilder revokeMsgContent = getPreContentForRevokeMsg(revokeMsgInfo).append("图片文件");
        WechatHelper.sendTextMsg(revokeMsgContent.toString(), WeChatContactConstants.FILE_HELPER);
        WechatHelper.sendImageFileMsg(revokeMsgInfo.getContent(), WeChatContactConstants.FILE_HELPER);
    }
}
