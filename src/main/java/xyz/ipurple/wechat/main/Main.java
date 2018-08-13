package xyz.ipurple.wechat.main;

import xyz.ipurple.wechat.listener.WechatListener;
import xyz.ipurple.wechat.login.core.Login;

public class Main {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(WechatListener.class);

    public static void main(String[] args) {
        Thread t = new Thread(new Login());
        t.run();
    }
}
