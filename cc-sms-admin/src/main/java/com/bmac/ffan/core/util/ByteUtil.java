package com.bmac.ffan.core.util;

import com.bmac.ffan.scheme.util.NumberStringUtil;

import java.io.*;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/21 10:16
 */
public class ByteUtil {

    /**
     * 字节数组转16进制字符串
     *
     * @param b
     *            字节数组
     * @return 16进制字符串
     */
    public static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer();
        String stmp;
        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0xFF);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString();
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param hex
     *            16进制字符串
     * @return 字节数组
     */
    public static byte[] hex2byte(String hex) {
        if (!isHexString(hex)) {
            return null;
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }
    /**
     * 校验是否是16进制字符串
     *
     * @param hex
     * @return
     */
    public static boolean isHexString(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return false;
        }
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            if (!isHexChar(c)) {
                return false;
            }
        }
        return true;
    }
    /**
     * 校验是否是16进制字符
     *
     * @param c
     * @return
     */
    private static boolean isHexChar(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }
    /**
     *
     * @param hex
     * @param endian 0：大端，1：小端
     * @return
     * @throws Exception
     */
    public static long hexStr2num(String hex, int endian) throws Exception {
        if(hex.length() == 2) {//byte
            return Integer.parseInt(hex, 16);
        }else if(hex.length() == 4 ){//串长度为4--short
            if(endian == 1) {
                return Short.parseShort(reverse(hex),16) ;
            }else{// endian == 1
                return Short.parseShort(hex,16) ;
            }
        }else if(hex.length() == 8){//int
            if(endian == 1) {
                return Integer.parseInt(reverse(hex),16) ;
            }else{// endian == 1
                return Integer.parseInt(hex,16) ;
            }
        }else if(hex.length() == 16){//long
            if(endian == 1) {
                return Long.parseLong(reverse(hex),16) ;
            }else{// endian == 1
                return Long.parseLong(hex,16) ;
            }
        }
        else{
            throw  new Exception("参数格式不对");
        }
    }

    /**
     * 字符串转反，比如01020304->04030201
     * @param hexstr
     * @return
     */
    private static String reverse(String hexstr){
        int len = hexstr.length();
        if(len % 2 == 0){
            StringBuilder sb = new StringBuilder();
        String[] arr = new String[len/2];
            for(int i=0; i< len/2; i++){
                String tmp = hexstr.substring(len-2*i -2, len-2*i);
                sb.append(tmp);
            }
            return sb.toString();
        }
        return null;
    }

    public static int ord(String s){
        int rt=s.toCharArray()[0];
        return rt;
    }

    /**
     * 获取EXCEL对应的列值
     * @param column column表示第几列
     * @return
     */
    public static String excelColumnValStr(int column){
        if(column < 1){
            return null;
        }else{
            StringBuilder tmp = new StringBuilder();
            int index = 0;
            do {
                index = column -1;
                int tmpInt = index % 26;
                tmp.insert(0, (char) (tmpInt + 65 ));
                column = index / 26;
                index = column;
            }while (index > 0);
            return tmp.toString();
        }
    }
    public static void main(String[] args) throws Exception {
      //  byte[] data ={ 0x2F, 0x05, 0x00, 0x03, (byte) 0xff, (byte) 0xff,(byte) 0xff };
        InputStream inputStream = new FileInputStream(new File("C:\\Users\\vicya\\data\\Tencent\\WeChat Files\\WeChat Files\\wxid_u328o8idrdt721\\Files\\firmware_0052.apk"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) > -1 ) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
        byte[] data = IOUtil.getByteArray(stream1);
        System.out.println("-1->"+byte2hex(data));
        System.out.println("-2->"+NumberStringUtil.bytesToHexString(data));
    }
}
