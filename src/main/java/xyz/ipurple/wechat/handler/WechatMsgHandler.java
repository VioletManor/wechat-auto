package xyz.ipurple.wechat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ipurple.wechat.base.constants.WechatMsgConstants;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;
import xyz.ipurple.wechat.handler.message.AbstractMessageHandler;
import xyz.ipurple.wechat.handler.message.IMessageHandler;
import xyz.ipurple.wechat.handler.message.image.expression.ImageExpressionMessageHandler;
import xyz.ipurple.wechat.handler.message.image.file.ImageFileMessageHandler;
import xyz.ipurple.wechat.handler.message.txt.TxtMessageHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zcy
 * @Description:
 * @Date: 2018/8/14 20:10
 * @Modified By:
 */
public class WechatMsgHandler extends AbstractMessageHandler implements IMessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(WechatMsgHandler.class);

    private static final Map<Integer, IMessageHandler> MESSAGE_HANDLERS = new HashMap<>(20);

    static {
        //注册消息处理器
        registerHandlers();
    }

    private static void registerHandlers() {
        //普通文本消息
        MESSAGE_HANDLERS.put(WechatMsgConstants.TEXT_MSG, new TxtMessageHandler());
        //图片文件
        MESSAGE_HANDLERS.put(WechatMsgConstants.IMAGE_FILE_MSG, new ImageFileMessageHandler());
        //图片表情
        MESSAGE_HANDLERS.put(WechatMsgConstants.IMAGE_EXPRESSION_MSG, new ImageExpressionMessageHandler());
        //语音
        //文件
        //视频
        //短视频
    }

    @Override
    public void receiveHandler(MsgEntity msgEntity) {
        IMessageHandler iMessageHandler = MESSAGE_HANDLERS.get(msgEntity.getMsgType());
        if (null != iMessageHandler) {
            iMessageHandler.receiveHandler(msgEntity);
        } else {
            logger.info("wechat#" + Thread.currentThread().getId() + "--msgType:{},无法找到匹配的消息处理器", msgEntity.getMsgType());
        }
        return;
    }

    /**
     * 撤回消息处理
     * @param msgEntity msgEntity
     */
    @Override
    public void revokeHandler(MsgEntity msgEntity) {
        int msgType = getMsgType(msgEntity);
        if (-1 == msgType) {
            return;
        }
        IMessageHandler iMessageHandler = MESSAGE_HANDLERS.get(msgType);
        if (null != iMessageHandler) {
            iMessageHandler.revokeHandler(msgEntity);
        } else {
            logger.info("wechat#" + Thread.currentThread().getId() + "--msgType:{},无法找到匹配的消息处理器", msgType);
        }
        return;
    }
}
