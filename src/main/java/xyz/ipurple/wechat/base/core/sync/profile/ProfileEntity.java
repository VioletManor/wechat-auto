package xyz.ipurple.wechat.base.core.sync.profile;

import java.util.Map;

/**
 * @ClassName: ProfileEntity
 * @Description: //TODO
 * @Author: zcy
 * @Date: 2018/8/8 9:59
 * @Version: 1.0
 */
public class ProfileEntity {
    private String Alias;
    private Map<String, Object> BindEmail;
    private Map<String, Object> BindMobile;
    private int BindUin;
    private int BitFlag;
    private int HeadImgUpdateFlag;
    private String HeadImgUrl;
    private Map<String, Object> NickName;
    private int PersonalCard;
    private int Sex;
    private String Signature;
    private int Status;
    private Map<String, Object> UserName;

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public Map<String, Object> getBindEmail() {
        return BindEmail;
    }

    public void setBindEmail(Map<String, Object> bindEmail) {
        BindEmail = bindEmail;
    }

    public Map<String, Object> getBindMobile() {
        return BindMobile;
    }

    public void setBindMobile(Map<String, Object> bindMobile) {
        BindMobile = bindMobile;
    }

    public int getBindUin() {
        return BindUin;
    }

    public void setBindUin(int bindUin) {
        BindUin = bindUin;
    }

    public int getBitFlag() {
        return BitFlag;
    }

    public void setBitFlag(int bitFlag) {
        BitFlag = bitFlag;
    }

    public int getHeadImgUpdateFlag() {
        return HeadImgUpdateFlag;
    }

    public void setHeadImgUpdateFlag(int headImgUpdateFlag) {
        HeadImgUpdateFlag = headImgUpdateFlag;
    }

    public String getHeadImgUrl() {
        return HeadImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        HeadImgUrl = headImgUrl;
    }

    public Map<String, Object> getNickName() {
        return NickName;
    }

    public void setNickName(Map<String, Object> nickName) {
        NickName = nickName;
    }

    public int getPersonalCard() {
        return PersonalCard;
    }

    public void setPersonalCard(int personalCard) {
        PersonalCard = personalCard;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Map<String, Object> getUserName() {
        return UserName;
    }

    public void setUserName(Map<String, Object> userName) {
        UserName = userName;
    }
}
