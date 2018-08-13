package xyz.ipurple.wechat.login.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import xyz.ipurple.wechat.base.core.WechatInfo;
import xyz.ipurple.wechat.base.core.init.ContactEntity;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;
import xyz.ipurple.wechat.base.util.Constants;
import xyz.ipurple.wechat.base.util.MatcheHelper;
import xyz.ipurple.wechat.base.util.WechatHelper;
import xyz.ipurple.wechat.listener.WechatListener;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: Login
 * @Description: //TODO
 * @Author: zcy
 * @Date: 2018/8/6 10:30
 * @Version: 1.0
 */
public class Login implements Runnable {
    public static final ThreadLocal<WechatInfo> WECHAT_INFO_THREAD_LOCAL = new ThreadLocal<WechatInfo>();
    //保存消息
    public static final ThreadLocal<ConcurrentHashMap<Long, MsgEntity>> MSG = new ThreadLocal<>();
    //保存用户
    public static final ThreadLocal<ConcurrentHashMap<String, ContactEntity>> CONTACT = new ThreadLocal<>();
    //保存群聊
    public static final ThreadLocal<ConcurrentHashMap<Long, ContactEntity>> GROUP_CHAT = new ThreadLocal<>();

    /**
     * sign in wechat
     */
    public void doLogin() {
        //获取二维码uuid
        String qrLoginUUID = WechatHelper.getQrLoginUUID();
        //扫码登陆
        String qrCodePath = WechatHelper.getQrCode(qrLoginUUID);
        WechatHelper.showQrCode(qrCodePath);

        WechatInfo wechatInfo = null;
        //等待扫码验证登陆
        while (true) {
            String res = WechatHelper.waitLogin(0, qrLoginUUID);
            String code = MatcheHelper.matches("window.code=(\\d+);", res);
            if (!Constants.HTTP_OK.equals(code)) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            //关闭二维码窗口
            WechatHelper.closeQrCode();
            //扫码后跳转至redirectUrl，并初始化基础信息
            wechatInfo = WechatHelper.redirect(res);
            break;
        }
        //登陆成功后进行初始化
        WechatHelper.init(wechatInfo);
        WECHAT_INFO_THREAD_LOCAL.set(wechatInfo);
        //状态更新
        WechatHelper.statusNotify(wechatInfo);
        //获取联系人
        String contactJson = WechatHelper.getContact(wechatInfo);
        Iterator<JSONObject> memberListIt = JSON.parseObject(contactJson).getObject("MemberList", List.class).iterator();

        while (memberListIt.hasNext()) {
            ContactEntity next = JSON.parseObject(memberListIt.next().toString(), ContactEntity.class);
            ConcurrentHashMap<String, ContactEntity> contactHashMap = getContactThreadLocal();
            contactHashMap.put(next.getUserName(), next);
        }
    }

    public void run() {
        try {
            //登陆
            doLogin();
            //监听消息
            new WechatListener().listen();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static WechatInfo getWechatInfoThreadLocal() {
        WechatInfo wechatInfo = WECHAT_INFO_THREAD_LOCAL.get();
        if (null == wechatInfo) {
            WECHAT_INFO_THREAD_LOCAL.set(wechatInfo = new WechatInfo());
        }
        return wechatInfo;
    }

    public static ConcurrentHashMap<String, ContactEntity> getContactThreadLocal() {
        ConcurrentHashMap<String, ContactEntity> contactHashMap = CONTACT.get();
        if (null == contactHashMap) {
            CONTACT.set(contactHashMap = new ConcurrentHashMap<>(200));
        }
        return contactHashMap;
    }

    public static ConcurrentHashMap<Long, MsgEntity> getMsgThreadLocal() {
        ConcurrentHashMap<Long, MsgEntity> msgHashMap = MSG.get();
        if (null == msgHashMap) {
            MSG.set(msgHashMap = new ConcurrentHashMap<>());
        }
        return msgHashMap;
    }
}
