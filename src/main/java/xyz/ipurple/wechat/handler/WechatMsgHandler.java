package xyz.ipurple.wechat.handler;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import xyz.ipurple.wechat.base.core.init.ContactEntity;
import xyz.ipurple.wechat.base.core.sync.SyncEntity;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;
import xyz.ipurple.wechat.base.util.MatcheHelper;
import xyz.ipurple.wechat.base.util.WeChatContactConstants;
import xyz.ipurple.wechat.base.util.WechatHelper;
import xyz.ipurple.wechat.base.util.WechatMsgConstants;
import xyz.ipurple.wechat.listener.WechatListener;
import xyz.ipurple.wechat.login.core.Login;

import java.util.Iterator;

/**
 * @author: zcy
 * @Description:
 * @Date: 2018/8/14 20:10
 * @Modified By:
 */
public class WechatMsgHandler {
    private static final Logger logger = Logger.getLogger(WechatMsgHandler.class);

    /**
     * 文本消息处理
     * @param syncEntity
     */
    public static void textMsgHandler(SyncEntity syncEntity) {
        Iterator<MsgEntity> msgIt = syncEntity.getAddMsgList().iterator();
        while (msgIt.hasNext()) {
            MsgEntity next = msgIt.next();
            if (next.getMsgType() == WechatMsgConstants.REVOKE_MSG) {
                //获取撤回的消息，并发送给文件助手
                logger.info("有撤回消息：" + next.getContent());
                getRevokeMsg(next);
            }
        }
    }

    /**
     * 图片文件处理
     * @param syncEntity
     */
    public static void imgFileMsgHandler(SyncEntity syncEntity) {
        Iterator<MsgEntity> msgIt = syncEntity.getAddMsgList().iterator();
        while (msgIt.hasNext()) {
            MsgEntity next = msgIt.next();
            if (next.getMsgType() == WechatMsgConstants.REVOKE_MSG) {
                //获取撤回的消息，并发送给文件助手
                logger.info("有图片文件消息撤回：" + next.getContent());
                getRevokeMsg(next);
            }
        }
    }

    /**
     * 获取到撤回的消息并发送给当前用户的文件助手
     * @param msgEntity
     */
    public static void getRevokeMsg(MsgEntity msgEntity) {
        String content = StringEscapeUtils.unescapeXml(msgEntity.getContent());
        //匹配撤回的消息id
        String revokeMsgid = MatcheHelper.matches("<msgid>(.*)</msgid>", content);
        String replaceMsg = MatcheHelper.matches("<replacemsg>(.*)</replacemsg>", content);
        //从消息列表中查找到撤回的消息
        MsgEntity oldMsg = Login.getMsgThreadLocal().get(Long.valueOf(revokeMsgid));
        //拼装消息
        StringBuffer revokeMsgContent = new StringBuffer("");
        if (Login.getContactThreadLocal().containsKey(oldMsg.getFromUserName())) {
            ContactEntity contactEntity = Login.getContactThreadLocal().get(oldMsg.getFromUserName());
            //哪个联系人或群组做的撤回
            String nickName = contactEntity.getNickName();
            //撤回的消息内容
            String revokeMsg = oldMsg.getContent();
            //备注名
            String remarkName = contactEntity.getRemarkName();
            if (oldMsg.getFromUserName().contains("@@")) {
                //群聊中撤回消息用户username
                String groupChatRevokeUserName = MatcheHelper.matches("(.*):<br/>", revokeMsg);
                revokeMsg = MatcheHelper.matches("<br/>(.*)", revokeMsg);
                if (Login.getContactThreadLocal().containsKey(groupChatRevokeUserName)) {
                    contactEntity = Login.getContactThreadLocal().get(groupChatRevokeUserName);
                    remarkName = contactEntity.getRemarkName();
                }
                //如果是群聊
                revokeMsgContent.append("群聊: ");
            }
            //撤回用户名
            String revokeUserName = MatcheHelper.matches("\\<\\!\\[CDATA\\[(?<text>[^\\]]*)\\]\\]\\>", content);
            revokeMsgContent.append(nickName)
                    .append("\r\n撤回人: " + revokeUserName)
                    .append("\r\n撤回消息内容为: \r\n")
                    .append(revokeMsg);
            WechatHelper.sendMsg(revokeMsgContent.toString(),oldMsg.getMsgType(), WeChatContactConstants.FILE_HELPER);
        }
        //TODO 将过期信息删除，节省空间

    }
}
