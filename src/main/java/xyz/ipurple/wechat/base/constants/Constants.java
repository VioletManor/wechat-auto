package xyz.ipurple.wechat.base.constants;

/**
 * @ClassName: Constants
 * @Description:
 * @Author: zcy
 * @Date: 2018/8/6 9:47
 * @Version: 1.0
 */
public class Constants {
    public static final Boolean RECEIVE_MSG_FLAG = true;
    public static final String HTTP_OK = "200";
    public static final String QRCODE_TEMP_DIR = "/usr/local/wechat/qrcode/";
    public static final String NEW_WECHAT_CAN_NOT_LOGIN = "1203";

    /**
     * 此url用于获取二维码uuid
     */
    public static final String JS_LOGIN_URL = "https://login.wx.qq.com/jslogin";
    /**
     * 获取二维码
     */
    public static final String LOGIN_QRCODE = "https://login.weixin.qq.com/qrcode/";
    /**
     * 登陆监听
     */
    public static final String WAIT_LOGIN = "https://login.wx.qq.com/cgi-bin/mmwebwx-bin/login";
    /**
     * 初始化
     */
    public static final String INIT_URL = "/webwxinit";

    public static final String[] SYNC_HOST = {
            "webpush.weixin.qq.com", "webpush.wechat.com", "webpush1.wechat.com",
            "webpush2.weixin.qq.com", "webpush2.wechat.com"};
    /**
     * 消息监听
     */
    public static final String SYNC_CHECK_URL = "/synccheck";
    /**
     * 消息同步
     */
    public static final String SYNC_URL = "/webwxsync";

    /**
     * 获取联系人
     */
    public static final String GET_CONTACT_URL = "/webwxgetcontact";
    /**
     * 状态更新
     */
    public static final String STATUS_NOTIFY_URL = "/webwxstatusnotify";
    /**
     * 发送消息
     */
    public static final String SEND_MSG_URL = "/webwxsendmsg";
    /**
     * 发送图片消息
     */
    public static final String SEND_MSG_IMG_URL = "/webwxsendmsgimg";
    /**
     * 发送图片表情
     */
    public static final String SEND_EMOTICON_URL = "/webwxsendemoticon";
    /**
     * 获取图片文件
     */
    public static final String GET_MSG_IMG_URL = "/webwxgetmsgimg";
    /**
     * 发送文件
     */
    public static final String SEND_APP_MSG = "/webwxsendappmsg";
}
