package xyz.ipurple.wechat.main;

import org.apache.commons.lang3.StringUtils;
import xyz.ipurple.wechat.base.util.MatcheHelper;
import xyz.ipurple.wechat.base.util.WechatHelper;
import xyz.ipurple.wechat.login.core.Login;

public class Main {

    public static void main(String[] args) {
        Thread t = new Thread(new Login());
        t.run();

    }
}
