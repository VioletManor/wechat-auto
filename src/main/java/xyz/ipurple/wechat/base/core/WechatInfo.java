package xyz.ipurple.wechat.base.core;


import com.alibaba.fastjson.JSONObject;
import xyz.ipurple.wechat.base.core.init.UserEntity;

/**
 * @author: zcy
 * @Description: 微信登陆相关信息存储
 * @Date: 2018/8/6 21:57
 * @Modified By:
 */
public class WechatInfo {
    private String skey;
    private String wxsid;
    private String wxuin;
    private String passicket;
    private String deviceId = "e20" + System.currentTimeMillis();
    private String cookie;
    private String syncKeyStr;
    private JSONObject syncKey;
    private JSONObject baseRequest;
    private UserEntity user;
    private String baseUrl;
    private String syncURL;

    @Override
    public String toString() {
        return "WechatInfo{" +
                "skey='" + skey + '\'' +
                ", wxsid='" + wxsid + '\'' +
                ", wxuin='" + wxuin + '\'' +
                ", passicket='" + passicket + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", cookie='" + cookie + '\'' +
                '}';
    }

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

    public String getSyncKeyStr() {
        return syncKeyStr;
    }

    public void setSyncKeyStr(String syncKeyStr) {
        this.syncKeyStr = syncKeyStr;
    }

    public JSONObject getSyncKey() {
        return syncKey;
    }

    public void setSyncKey(JSONObject syncKey) {
        this.syncKey = syncKey;
    }

    public JSONObject getBaseRequest() {
        return baseRequest;
    }

    public void setBaseRequest(JSONObject baseRequest) {
        this.baseRequest = baseRequest;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSyncURL() {
        return syncURL;
    }

    public void setSyncURL(String syncURL) {
        this.syncURL = syncURL;
    }
}
