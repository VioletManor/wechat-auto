package xyz.ipurple.wechat.base.util;

/**
 * @author: zcy
 * @Description:
 * @Date: 2018/8/6 21:32
 * @Modified By:
 */
public class HttpResponse {
    private String cookie;
    private String content;
    private String jSessionId;
    private String cookieUser;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getjSessionId() {
        return jSessionId;
    }

    public void setjSessionId(String jSessionId) {
        this.jSessionId = jSessionId;
    }

    public String getCookieUser() {
        return cookieUser;
    }

    public void setCookieUser(String cookieUser) {
        this.cookieUser = cookieUser;
    }
}
