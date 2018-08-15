package xyz.ipurple.wechat.base.core.revoke;

/**
 * @ClassName: RevokeMsgInfo
 * @Description: 撤回消息信息
 * @Author: zcy
 * @Date: 2018/8/15 8:53
 * @Version: 1.0
 */
public class RevokeMsgInfo {
    /**
     * 执行撤回操作的联系人或群组
     * 均显示昵称
     */
    private String nickName;
    /**
     * 具体撤回人名字（显示备注名，若没有备注名称则显示昵称）
     */
    private String revokeUserName;
    /**
     * 撤回的消息
     */
    private String content;
    /**
     * 消息类型
     */
    private int msgType;
    /**
     * 群聊标记（1：是，2：普通联系人）
     */
    private int chatGroupFlag;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRevokeUserName() {
        return revokeUserName;
    }

    public void setRevokeUserName(String revokeUserName) {
        this.revokeUserName = revokeUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getChatGroupFlag() {
        return chatGroupFlag;
    }

    public void setChatGroupFlag(int chatGroupFlag) {
        this.chatGroupFlag = chatGroupFlag;
    }
}
