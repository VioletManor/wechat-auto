package xyz.ipurple.wechat.base.util;

import org.apache.log4j.Logger;
import xyz.ipurple.wechat.listener.WechatListener;

import java.io.*;

/**
 * @author: zcy
 * @Description:
 * @Date: 2018/8/12 11:43
 * @Modified By:
 */
public class FileHelper {
    private static final Logger logger = Logger.getLogger(FileHelper.class);

    public static void createFile(InputStream in, String path, String fileName) {
        FileOutputStream fos = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(path + File.separator + fileName);
            fos = new FileOutputStream(file);
            int l = -1;
            byte[] tmp = new byte[1024];
            while ((l = in.read(tmp)) != -1) {
                fos.write(tmp, 0, l);
            }
            fos.flush();
        } catch (FileNotFoundException e) {
            logger.error("file not found", e);
        } catch (IOException e) {
            logger.error("io exception", e);
            e.printStackTrace();
        } finally {
            close(in, fos);
        }

    }

    public static void close(InputStream in, OutputStream os) {
        try {
            if (os != null) {
                os.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
