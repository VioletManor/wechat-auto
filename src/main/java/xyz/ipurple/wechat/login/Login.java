package xyz.ipurple.wechat.login;

import org.apache.commons.beanutils.BeanUtils;
import xyz.ipurple.wechat.base.constants.Constants;
import xyz.ipurple.wechat.base.core.WechatInfo;
import xyz.ipurple.wechat.base.util.MatcheHelper;
import xyz.ipurple.wechat.base.util.WechatHelper;
import xyz.ipurple.wechat.listener.WechatListener;

import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName: Login
 * @Description: 登陆
 * @Author: zcy
 * @Date: 2018/8/6 10:30
 * @Version: 1.0
 */
public class Login implements Runnable {

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
            String result = WechatHelper.waitLogin(0, qrLoginUUID);
            String code = MatcheHelper.matches("window.code=(\\d+);", result);
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
            WechatHelper.deleteQrCode(qrCodePath);
            //扫码后跳转至redirectUrl，并初始化基础信息
            wechatInfo = WechatHelper.redirect(result);
            break;
        }
        //登陆成功后进行初始化
        WechatHelper.init(wechatInfo);
        WechatInfo wechatInfoThreadLocal = UserContext.getWechatInfoThreadLocal();
        try {
            BeanUtils.copyProperties(wechatInfoThreadLocal, wechatInfo);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //开启状态更新
        WechatHelper.statusNotify(wechatInfo);
        //获取联系人
        WechatHelper.getContact(wechatInfo);
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
}
