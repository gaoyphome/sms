package com.bmac.ffan.scheme.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * 计算文件的MD5算法类
 * @author xiongrl
 *
 */
public class FileDigest {
	/**
	  * 
	  * 方法名称		:	getFileMD5 
	  * 方法描述 		:	获取单个文件的MD5值！
	  * 
	  * 返回值类型	：	String
	  * @Author		:GUOYJ 
	  * @param file
	  * @return 16字节的MD5字符串
	  * @exception 
	  * @since  1.0.0
	  * @Create Date: 2013-1-10 下午2:41:08
	 */
	public static String getFileMD5(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

	/**
	  * 
	  * 方法名称		:	getDirMD5 
	  * 方法描述 		:	获取文件夹中文件的MD5值
	  * 
	  * 返回值类型	：	Map<String,String>
	  * @Author		:GUOYJ 
	  * @param file
	  * @param listChild;当值为true时递归子目录中的文件
	  * @return 
	  * @exception 
	  * @since  1.0.0
	  * @Create Date: 2013-1-10 下午2:39:53
	 */
	public static Map<String, String> getDirMD5(File file, boolean listChild) {
		if (!file.isDirectory()) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		String md5;
		File files[] = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory() && listChild) {
				map.putAll(getDirMD5(f, listChild));
			} else {
				md5 = getFileMD5(f);
				if (md5 != null) {
					map.put(f.getPath(), md5);
				}
			}
		}
		return map;
	}

	/**
	 * 对文件进行MD5运算
	 * 返回32位字符串
	 */
	public static String calculateMD5(String filePathNm) {
		InputStream fis = null;
		byte[] b;
		StringBuilder sb = null;
		char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A', 'B', 'C', 'D', 'E', 'F' };
		try {
			fis = new FileInputStream(filePathNm);
			byte[] buffer = new byte[1024];
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			int numRead = 0;
			while ((numRead = fis.read(buffer)) > 0) {
				md5.update(buffer, 0, numRead);
			}
			b = md5.digest();
			sb = new StringBuilder(b.length * 2);
			for (int i = 0; i < b.length; i++) {
				sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
				sb.append(hexChar[b[i] & 0x0f]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
					fis = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
}
