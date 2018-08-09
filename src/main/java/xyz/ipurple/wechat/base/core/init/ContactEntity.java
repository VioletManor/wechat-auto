package xyz.ipurple.wechat.base.core.init;

import java.util.List;

/**
 * @author: zcy
 * @Description:
 * @Date: 2018/8/6 20:17
 * @Modified By:
 */
public class ContactEntity {
    private String Alias;
    private int AppAccountFlag;
    /**
     * contactFlag为3且这个有值
     * 可能是联系人
     */
    private Long AttrStatus;
    private int ChatRoomId;
    /**
     * 市
     */
    private String City;
    /**
     * 联系人标记
     * 0：群聊； 1: 微信官方; 3:订阅号、联系人、群聊
     */
    private int ContactFlag;
    private String DisplayName;
    private String EncryChatRoomId;
    private String HeadImgUrl;
    private int HideInputBarFlag;
    private int IsOwner;
    private String KeyWord;
    private int MemberCount;
    private List<MemberEntity> MemberList;
    private String NickName;
    private int OwnerUin;
    /**
     * 名称缩写
     * 例：WJCSZS
     */
    private String PYInitial;
    /**
     * 全拼
     * 例：wenjianchuanshuzhushou
     */
    private String PYQuanPin;
    /**
     * 省
     */
    private String Province;
    private String RemarkName;
    private String RemarkPYInitial;
    private String RemarkPYQuanPin;
    /**
     * 2：女
     */
    private int Sex;
    private String Signature;
    private int SnsFlag;
    private int StarFriend;
    private int Statues;
    private int Uin;
    private int UniFriend;
    /**
     * 用户名
     */
    private String UserName;
    private String VerifyFlag;

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public int getAppAccountFlag() {
        return AppAccountFlag;
    }

    public void setAppAccountFlag(int appAccountFlag) {
        AppAccountFlag = appAccountFlag;
    }

    public Long getAttrStatus() {
        return AttrStatus;
    }

    public void setAttrStatus(Long attrStatus) {
        AttrStatus = attrStatus;
    }

    public int getChatRoomId() {
        return ChatRoomId;
    }

    public void setChatRoomId(int chatRoomId) {
        ChatRoomId = chatRoomId;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getContactFlag() {
        return ContactFlag;
    }

    public void setContactFlag(int contactFlag) {
        ContactFlag = contactFlag;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getEncryChatRoomId() {
        return EncryChatRoomId;
    }

    public void setEncryChatRoomId(String encryChatRoomId) {
        EncryChatRoomId = encryChatRoomId;
    }

    public String getHeadImgUrl() {
        return HeadImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        HeadImgUrl = headImgUrl;
    }

    public int getHideInputBarFlag() {
        return HideInputBarFlag;
    }

    public void setHideInputBarFlag(int hideInputBarFlag) {
        HideInputBarFlag = hideInputBarFlag;
    }

    public int getIsOwner() {
        return IsOwner;
    }

    public void setIsOwner(int isOwner) {
        IsOwner = isOwner;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public int getMemberCount() {
        return MemberCount;
    }

    public void setMemberCount(int memberCount) {
        MemberCount = memberCount;
    }

    public List<MemberEntity> getMemberList() {
        return MemberList;
    }

    public void setMemberList(List<MemberEntity> memberList) {
        MemberList = memberList;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public int getOwnerUin() {
        return OwnerUin;
    }

    public void setOwnerUin(int ownerUin) {
        OwnerUin = ownerUin;
    }

    public String getPYInitial() {
        return PYInitial;
    }

    public void setPYInitial(String PYInitial) {
        this.PYInitial = PYInitial;
    }

    public String getPYQuanPin() {
        return PYQuanPin;
    }

    public void setPYQuanPin(String PYQuanPin) {
        this.PYQuanPin = PYQuanPin;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String remarkName) {
        RemarkName = remarkName;
    }

    public String getRemarkPYInitial() {
        return RemarkPYInitial;
    }

    public void setRemarkPYInitial(String remarkPYInitial) {
        RemarkPYInitial = remarkPYInitial;
    }

    public String getRemarkPYQuanPin() {
        return RemarkPYQuanPin;
    }

    public void setRemarkPYQuanPin(String remarkPYQuanPin) {
        RemarkPYQuanPin = remarkPYQuanPin;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public int getSnsFlag() {
        return SnsFlag;
    }

    public void setSnsFlag(int snsFlag) {
        SnsFlag = snsFlag;
    }

    public int getStarFriend() {
        return StarFriend;
    }

    public void setStarFriend(int starFriend) {
        StarFriend = starFriend;
    }

    public int getStatues() {
        return Statues;
    }

    public void setStatues(int statues) {
        Statues = statues;
    }

    public int getUin() {
        return Uin;
    }

    public void setUin(int uin) {
        Uin = uin;
    }

    public int getUniFriend() {
        return UniFriend;
    }

    public void setUniFriend(int uniFriend) {
        UniFriend = uniFriend;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getVerifyFlag() {
        return VerifyFlag;
    }

    public void setVerifyFlag(String verifyFlag) {
        VerifyFlag = verifyFlag;
    }
}