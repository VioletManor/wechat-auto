package xyz.ipurple.wechat.handler.message;

import org.apache.commons.lang3.StringEscapeUtils;
import xyz.ipurple.wechat.base.constants.WechatMsgConstants;
import xyz.ipurple.wechat.base.core.init.ContactEntity;
import xyz.ipurple.wechat.base.core.revoke.RevokeMsgInfo;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;
import xyz.ipurple.wechat.base.util.MatcheHelper;
import xyz.ipurple.wechat.login.UserContext;

/**
 * @ClassName: AbstractMessageHandler
 * @Description: 消息处理
 * @Author: zcy
 * @Date: 2018/8/31 11:26
 * @Version: 1.0
 */
public abstract class AbstractMessageHandler {

    //获取撤回人信息
    protected static RevokeMsgInfo getRevokeMsgInfo(MsgEntity msgEntity) {
        String content = StringEscapeUtils.unescapeXml(msgEntity.getContent());
        //匹配撤回的消息id
        String revokeMsgid = MatcheHelper.matches("<msgid>(.*)</msgid>", content);
        String replaceMsg = MatcheHelper.matches("<replacemsg>(.*)</replacemsg>", content);
        //从消息列表中查找到撤回的消息
        MsgEntity oldMsg = UserContext.getMsgThreadLocal().get(Long.valueOf(revokeMsgid));
        //获取撤回消息相关信息
        if (oldMsg != null && UserContext.getContactThreadLocal().containsKey(oldMsg.getFromUserName())) {
            RevokeMsgInfo revokeMsgInfo = new RevokeMsgInfo();
            //获取联系人信息
            ContactEntity contactEntity = UserContext.getContactThreadLocal().get(oldMsg.getFromUserName());
            //哪个联系人或群组做的撤回
            String nickName = contactEntity.getNickName();
            //撤回的消息内容
            String revokeMsg = oldMsg.getContent();
            //撤回用户名
            String revokeUserName = MatcheHelper.matches("\\<\\!\\[CDATA\\[(?<text>[^\\]]*)\\]\\]\\>", content);
            //如果是图片表情 截取md5值
            if (oldMsg.getMsgType() == WechatMsgConstants.IMAGE_EXPRESSION_MSG) {
                revokeMsg = MatcheHelper.matches("md5=\"(.*?)\"", revokeMsg);
            } else if (oldMsg.getFromUserName().contains("@@")) {
                //群聊中撤回消息用户username
                revokeMsg = MatcheHelper.matches("<br/>(.*)", revokeMsg);
                revokeMsgInfo.setChatGroupFlag(1);
            }

            revokeMsgInfo.setNickName(nickName);
            revokeMsgInfo.setRevokeUserName(revokeUserName);
            revokeMsgInfo.setContent(revokeMsg);
            revokeMsgInfo.setMsgType(oldMsg.getMsgType());
            return revokeMsgInfo;
        }
        return null;
    }

    protected static int getMsgType(MsgEntity msgEntity) {
        String content = StringEscapeUtils.unescapeXml(msgEntity.getContent());
        String revokeMsgid = MatcheHelper.matches("<msgid>(.*)</msgid>", content);
        //从消息列表中查找到撤回的消息
        MsgEntity oldMsg = UserContext.getMsgThreadLocal().get(Long.valueOf(revokeMsgid));
        if (oldMsg != null && UserContext.getContactThreadLocal().containsKey(oldMsg.getFromUserName())) {
            return oldMsg.getMsgType();
        }
        return -1;
    }

    /**
     * 获取要发送的撤回消息前缀内容
     * @param revokeMsgInfo
     * @return
     */
    protected static StringBuilder getPreContentForRevokeMsg(RevokeMsgInfo revokeMsgInfo) {
        StringBuilder revokeMsgContent = new StringBuilder();
        if (1 == revokeMsgInfo.getChatGroupFlag()) {
            revokeMsgContent.append("群聊");
        }
        revokeMsgContent.append("昵称: ")
                .append(revokeMsgInfo.getNickName())
                .append("\r\n撤回人: " + revokeMsgInfo.getRevokeUserName())
                .append("\r\n撤回消息内容为: \r\n");
        return revokeMsgContent;
    }
}
