package xyz.ipurple.wechat.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ipurple.wechat.login.Login;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Thread t = new Thread(new Login());
        t.run();
    }
}
