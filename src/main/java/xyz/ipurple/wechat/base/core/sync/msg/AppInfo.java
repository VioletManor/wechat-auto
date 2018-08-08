package xyz.ipurple.wechat.base.core.sync.msg;

/**
 * @ClassName: AppInfo
 * @Description: //TODO
 * @Author: zcy
 * @Date: 2018/8/8 10:04
 * @Version: 1.0
 */
public class AppInfo {
    private String AppID;
    private int Type;

    public String getAppID() {
        return AppID;
    }

    public void setAppID(String appID) {
        AppID = appID;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
