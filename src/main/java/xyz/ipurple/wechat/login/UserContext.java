package xyz.ipurple.wechat.login;

import xyz.ipurple.wechat.base.core.WechatInfo;
import xyz.ipurple.wechat.base.core.init.ContactEntity;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserContext
 * @Description: 用户上下文信息
 * @Author: zcy
 * @Date: 2018/8/23 11:20
 * @Version: 1.0
 */
public class UserContext {

    private static final ThreadLocal<WechatInfo> WECHAT_INFO = new ThreadLocal<WechatInfo>();
    //保存消息
    private static final ThreadLocal<Map<Long, MsgEntity>> MSG = new ThreadLocal<>();
    //保存用户
    private static final ThreadLocal<Map<String, ContactEntity>> CONTACT = new ThreadLocal<>();

    public static WechatInfo getWechatInfoThreadLocal() {
        WechatInfo wechatInfo = WECHAT_INFO.get();
        if (null == wechatInfo) {
            WECHAT_INFO.set(wechatInfo = new WechatInfo());
        }
        return wechatInfo;
    }

    public static Map<String, ContactEntity> getContactThreadLocal() {
        Map<String, ContactEntity> contactHashMap = CONTACT.get();
        if (null == contactHashMap) {
            CONTACT.set(contactHashMap = new HashMap<>(200));
        }
        return contactHashMap;
    }

    public static Map<Long, MsgEntity> getMsgThreadLocal() {
        Map<Long, MsgEntity> msgHashMap = MSG.get();
        if (null == msgHashMap) {
            MSG.set(msgHashMap = new HashMap<>());
        }
        return msgHashMap;
    }
}
