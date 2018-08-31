package xyz.ipurple.wechat.login;

import xyz.ipurple.wechat.base.core.WechatInfo;
import xyz.ipurple.wechat.base.core.init.ContactEntity;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;
import xyz.ipurple.wechat.base.util.DateUtil;

import java.util.HashMap;
import java.util.Iterator;
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
            MSG.set(msgHashMap = new HashMap<>(500));
        }
        return msgHashMap;
    }

    public static void clearExpireMsg() {
        Map<Long, MsgEntity> msgHashMap = MSG.get();
        if (msgHashMap.size() > 400) {
            System.out.println("size:"+msgHashMap.size());
            new Thread(() -> {
                Iterator<Map.Entry<Long, MsgEntity>> msgIt = msgHashMap.entrySet().iterator();
                while (msgIt.hasNext()) {
                    Map.Entry<Long, MsgEntity> next = msgIt.next();
                    Long createTime = next.getValue().getCreateTime();
                    Long curTime = DateUtil.getSecondTimestamp();
                    if (curTime - createTime > 60 * 2) {

                        msgIt.remove();
                    }
                }
            });
        }
    }
}
