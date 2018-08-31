package xyz.ipurple.wechat.handler.message;

import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;

/**
 * @author: zcy
 * @Description: 消息处理
 * @Date: 2018/8/31 11:21
 * @Modified By:
 */
public interface IMessageHandler {
    /**
     * 处理接收的消息
     * @param msgEntity msg
     */
    void receiveHandler(MsgEntity msgEntity);
    /**
     * 处理撤回的消息
     * @param msgEntity msg
     */
    void revokeHandler(MsgEntity msgEntity);
}
