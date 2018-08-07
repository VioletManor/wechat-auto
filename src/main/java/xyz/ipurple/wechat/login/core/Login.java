package xyz.ipurple.wechat.login.core;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import xyz.ipurple.wechat.base.core.WechatInfo;
import xyz.ipurple.wechat.base.util.*;

/**
 * @ClassName: Login
 * @Description: //TODO
 * @Author: zcy
 * @Date: 2018/8/6 10:30
 * @Version: 1.0
 */
public class Login {

    /**
     * sign in wechat
     */
    public void doLogin(){
        //获取二维码uuid
        String qrLoginUUID = WechatHelper.getQrLoginUUID();
        //扫码登陆
        String qrCodePath = WechatHelper.getQrCode(qrLoginUUID);
        WechatHelper.showQrCode(qrCodePath);
        //等待扫码验证登陆
        while (true) {
            String res = WechatHelper.waitLogin(0, qrLoginUUID);
            System.out.println(res);
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
            WechatInfo wechatInfo = WechatHelper.redirect(res);
            WechatHelper.init(wechatInfo);

            break;
        }
    }
}
