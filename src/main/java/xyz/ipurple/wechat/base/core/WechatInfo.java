package xyz.ipurple.wechat.base.core;


/**
 * @author: zcy
 * @Description:
 * @Date: 2018/8/6 21:57
 * @Modified By:
 */
public class WechatInfo {
    private String skey;
    private String wxsid;
    private String wxuin;
    private String passicket;
    private String deviceId = "e" + System.currentTimeMillis();
    private String cookie;

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public String getWxsid() {
        return wxsid;
    }

    public void setWxsid(String wxsid) {
        this.wxsid = wxsid;
    }

    public String getWxuin() {
        return wxuin;
    }

    public void setWxuin(String wxuin) {
        this.wxuin = wxuin;
    }

    public String getPassicket() {
        return passicket;
    }

    public void setPassicket(String passicket) {
        this.passicket = passicket;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
