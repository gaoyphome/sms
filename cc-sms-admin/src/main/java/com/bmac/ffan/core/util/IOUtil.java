package com.bmac.ffan.core.util;

import com.bmac.ffan.scheme.util.NumberStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2018/1/30 23:02
 */
public class IOUtil {
    private static final Logger logger = LoggerFactory.getLogger(IOUtil.class);
    public static byte[] getByteArray(InputStream inputStream){
        BufferedInputStream in = null;
        byte[] data = null;

        try {
            in = new BufferedInputStream(inputStream);
            byte[] e = new byte[2048];

            int bytesread1;
            while((bytesread1 = in.read(e)) != -1) {
                if(data == null) {
                    data = NumberStringUtil.getBytes(e, 0, bytesread1);
                } else {
                    data = NumberStringUtil.byteArrayAdd(data, NumberStringUtil.getBytes(e, 0, bytesread1));
                }
            }

            in.close();
        } catch (FileNotFoundException var16) {
            var16.printStackTrace();
        } catch (IOException var17) {
            var17.printStackTrace();
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (IOException var15) {
                logger.error("close stream error!", var15);
            }

        }
        return data;
    }
}
