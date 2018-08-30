package xyz.ipurple.wechat.base.core.init;

/**
 * @author: zcy
 * @Description:
 * @Date: 2018/8/6 20:39
 * @Modified By:
 */
public class MemberEntity {
    private Long AttrStatus;
    private String DisplayName;
    private String KeyWord;
    private Long MemberStatus;
    private String NickName;
    private String PYInitial;
    private String PYQuanPin;
    private String RemarkPYInitial;
    private String RemarkPYQuanPin;
    private Long Uin;
    private String UserName;

    public Long getAttrStatus() {
        return AttrStatus;
    }

    public void setAttrStatus(Long attrStatus) {
        AttrStatus = attrStatus;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public Long getMemberStatus() {
        return MemberStatus;
    }

    public void setMemberStatus(Long memberStatus) {
        MemberStatus = memberStatus;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
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

    public Long getUin() {
        return Uin;
    }

    public void setUin(Long uin) {
        Uin = uin;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
