package xyz.ipurple.wechat.base.core.send.msg;

/**
 * @ClassName: SendMsgDto
 * @Description: //发送消息
 * @Author: zcy
 * @Date: 2018/8/13 12:47
 * @Version: 1.0
 */
public class SendMsgDto {
    /**
     * 与localid一样， 时间戳+随机数
     */
    private String ClientMsgId;
    /**
     * 发送内容
     */
    private String Content;
    /**
     * 发送人username
     */
    private String FromUserName;
    private String LocalID;
    private String ToUserName;
    /**
     * 消息类型
     */
    private int Type;

    public String getClientMsgId() {
        return ClientMsgId;
    }

    public void setClientMsgId(String clientMsgId) {
        ClientMsgId = clientMsgId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getLocalID() {
        return LocalID;
    }

    public void setLocalID(String localID) {
        LocalID = localID;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
