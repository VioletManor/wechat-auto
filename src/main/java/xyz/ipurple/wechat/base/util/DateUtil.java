package xyz.ipurple.wechat.base.util;

import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description:
 * @Author: zcy
 * @Date: 2018/8/31 18:24
 * @Version: 1.0
 */
public class DateUtil {
    /**
     * 获取精确到秒的时间戳
     *
     * @return
     */
    public static Long getSecondTimestamp() {
        String timestamp = String.valueOf(new Date().getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Long.valueOf(timestamp.substring(0, length - 3));
        } else {
            return 0L;
        }

    }
}
