package xyz.ipurple.wechat.base.core.init;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: zcy
 * @Description:
 * @Date: 2018/8/6 20:43
 * @Modified By:
 */
public class WechatInitEntity {
    private BaseResponse BaseResponse;
    private String ChatSet;
    private Long ClickReportInterval;
    private Long ClientVersion;
    private List<ContactEntity> ContactList;
    private Long Count;
    private Long GrayScale;
    private Long InviteStartCount;
    /**
     * 订阅号？
     */
    private Long MPSubscribeMsgCount;
    private List MPSubscribeMsgList;
    private String SKey;
    private JSONObject SyncKey;
    private Long SystemTime;
    private UserEntity User;

    @Override
    public String toString() {
        return "WechatInitEntity{" +
                "BaseResponse=" + BaseResponse +
                ", ChatSet='" + ChatSet + '\'' +
                ", ClickReportInterval=" + ClickReportInterval +
                ", ClientVersion=" + ClientVersion +
                ", ContactList=" + ContactList +
                ", Count=" + Count +
                ", GrayScale=" + GrayScale +
                ", InviteStartCount=" + InviteStartCount +
                ", MPSubscribeMsgCount=" + MPSubscribeMsgCount +
                ", MPSubscribeMsgList=" + MPSubscribeMsgList +
                ", SKey='" + SKey + '\'' +
                ", SyncKey=" + SyncKey +
                ", SystemTime=" + SystemTime +
                ", User=" + User +
                '}';
    }

    public xyz.ipurple.wechat.base.core.init.BaseResponse getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(xyz.ipurple.wechat.base.core.init.BaseResponse baseResponse) {
        BaseResponse = baseResponse;
    }

    public String getChatSet() {
        return ChatSet;
    }

    public void setChatSet(String chatSet) {
        ChatSet = chatSet;
    }

    public Long getClickReportInterval() {
        return ClickReportInterval;
    }

    public void setClickReportInterval(Long clickReportInterval) {
        ClickReportInterval = clickReportInterval;
    }

    public Long getClientVersion() {
        return ClientVersion;
    }

    public void setClientVersion(Long clientVersion) {
        ClientVersion = clientVersion;
    }

    public List<ContactEntity> getContactList() {
        return ContactList;
    }

    public void setContactList(List<ContactEntity> contactList) {
        ContactList = contactList;
    }

    public Long getCount() {
        return Count;
    }

    public void setCount(Long count) {
        Count = count;
    }

    public Long getGrayScale() {
        return GrayScale;
    }

    public void setGrayScale(Long grayScale) {
        GrayScale = grayScale;
    }

    public Long getInviteStartCount() {
        return InviteStartCount;
    }

    public void setInviteStartCount(Long inviteStartCount) {
        InviteStartCount = inviteStartCount;
    }

    public Long getMPSubscribeMsgCount() {
        return MPSubscribeMsgCount;
    }

    public void setMPSubscribeMsgCount(Long MPSubscribeMsgCount) {
        this.MPSubscribeMsgCount = MPSubscribeMsgCount;
    }

    public List getMPSubscribeMsgList() {
        return MPSubscribeMsgList;
    }

    public void setMPSubscribeMsgList(List MPSubscribeMsgList) {
        this.MPSubscribeMsgList = MPSubscribeMsgList;
    }

    public String getSKey() {
        return SKey;
    }

    public void setSKey(String SKey) {
        this.SKey = SKey;
    }

    public JSONObject getSyncKey() {
        return SyncKey;
    }

    public void setSyncKey(JSONObject syncKey) {
        SyncKey = syncKey;
    }

    public Long getSystemTime() {
        return SystemTime;
    }

    public void setSystemTime(Long systemTime) {
        SystemTime = systemTime;
    }

    public UserEntity getUser() {
        return User;
    }

    public void setUser(UserEntity user) {
        User = user;
    }
}
