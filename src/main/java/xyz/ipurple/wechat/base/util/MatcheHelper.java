package xyz.ipurple.wechat.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: MatcheHelper
 * @Description: //TODO
 * @Author: zcy
 * @Date: 2018/8/6 15:21
 * @Version: 1.0
 */
public class MatcheHelper {
    public static String matches(String regex, String res) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(res);
        StringBuffer sb = new StringBuffer();
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                sb.append(matcher.group(i));
            }
        }
        return sb.toString();
    }
}
