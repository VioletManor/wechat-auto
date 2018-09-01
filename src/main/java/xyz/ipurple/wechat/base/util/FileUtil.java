package xyz.ipurple.wechat.base.util;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: zcy
 * @Description:
 * @Date: 2018/8/31 23:15
 * @Modified By:
 */
public class FileUtil {
    public static String getImageBase64(String filePath) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(data);//返回Base64编码过的字节数组字符串
        return "data:image/jpg;base64,"+encode;
    }
}
