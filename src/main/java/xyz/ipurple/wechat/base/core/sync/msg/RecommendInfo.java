package xyz.ipurple.wechat.base.core.sync.msg;

/**
 * @ClassName: RecommendInfo
 * @Description: //TODO
 * @Author: zcy
 * @Date: 2018/8/8 10:40
 * @Version: 1.0
 */
public class RecommendInfo {
    private String Alias;
    private int AttrStatus;
    private String City;
    private String Content;
    private String NickName;
    private int OpCode;
    private String Province;
    private int QQNum;
    private int Scene;
    private int Sex;
    private String Signature;
    private String Ticket;
    private String UserName;
    private String VerifyFlag;

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public int getAttrStatus() {
        return AttrStatus;
    }

    public void setAttrStatus(int attrStatus) {
        AttrStatus = attrStatus;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public int getOpCode() {
        return OpCode;
    }

    public void setOpCode(int opCode) {
        OpCode = opCode;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public int getQQNum() {
        return QQNum;
    }

    public void setQQNum(int QQNum) {
        this.QQNum = QQNum;
    }

    public int getScene() {
        return Scene;
    }

    public void setScene(int scene) {
        Scene = scene;
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

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
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
