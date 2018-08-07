package xyz.ipurple.wechat.base.core;

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
    private int ClickReportInterval;
    private int ClientVersion;
    private List<ContactEntity> ContactList;
    private int Count;
    private int GrayScale;
    private int InviteStartCount;
    /**
     * 订阅号？
     */
    private int MPSubscribeMsgCount;
    private List MPSubscribeMsgList;
    private String SKey;
    private List SyncKey;
    private Long SystemTime;
    private UserEntity User;

    public xyz.ipurple.wechat.base.core.BaseResponse getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(xyz.ipurple.wechat.base.core.BaseResponse baseResponse) {
        BaseResponse = baseResponse;
    }

    public String getChatSet() {
        return ChatSet;
    }

    public void setChatSet(String chatSet) {
        ChatSet = chatSet;
    }

    public int getClickReportInterval() {
        return ClickReportInterval;
    }

    public void setClickReportInterval(int clickReportInterval) {
        ClickReportInterval = clickReportInterval;
    }

    public int getClientVersion() {
        return ClientVersion;
    }

    public void setClientVersion(int clientVersion) {
        ClientVersion = clientVersion;
    }

    public List<ContactEntity> getContactList() {
        return ContactList;
    }

    public void setContactList(List<ContactEntity> contactList) {
        ContactList = contactList;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getGrayScale() {
        return GrayScale;
    }

    public void setGrayScale(int grayScale) {
        GrayScale = grayScale;
    }

    public int getInviteStartCount() {
        return InviteStartCount;
    }

    public void setInviteStartCount(int inviteStartCount) {
        InviteStartCount = inviteStartCount;
    }

    public int getMPSubscribeMsgCount() {
        return MPSubscribeMsgCount;
    }

    public void setMPSubscribeMsgCount(int MPSubscribeMsgCount) {
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

    public List getSyncKey() {
        return SyncKey;
    }

    public void setSyncKey(List syncKey) {
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
