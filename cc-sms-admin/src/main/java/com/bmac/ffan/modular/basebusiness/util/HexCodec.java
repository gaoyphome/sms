package com.bmac.ffan.modular.basebusiness.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @brief 这是一个工具类。
 * 
 *        这个类可以用来来执行十六进制编码/解码。 可以进行字符串对象和字节数组进行相互转换
 * 
 * @author {like}
 * @version {1.0}
 * @see
 * @since JDK{1.6}
 * @create Date: 2013-1-30 下午5:29:33
 */

public class HexCodec {

	static final char[] HEX = new char[] { '0', '1', '2', '3', '4', '5', '6','7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' }; // !<HEX字节数组

	/**
	 * 
	 * 方法名称 : hexDecode 方法描述 : 将字符串对象转化为字节对象
	 * 
	 * @param buffer
	 * @return String
	 * @since 1.0.0
	 * @create Date: 2013-1-30 下午5:29:33
	 */
	public static String hexEncode(byte[] buffer) {
		if (buffer.length == 0) {
			return "";
		}
		int holder = 0;
		char[] chars = new char[buffer.length * 2];
		for (int i = 0; i < buffer.length; i++) {
			holder = (buffer[i] & 0xf0) >> 4;
			chars[i * 2] = HEX[holder];
			holder = buffer[i] & 0x0f;
			chars[(i * 2) + 1] = HEX[holder];
		}
		return new String(chars);
	}

	public  static String  asciiTOHex(String ascii){


		StringBuffer sb  = new StringBuffer();
		for (int i = 0; i < (ascii.length()/2); i++) {

			sb.append( (char) Integer.parseInt(ascii.substring(i*2, i*2+2),16));
		}

		return sb.toString();
	}



	/**
	 * 
	 * 方法名称 : hexEncode 方法描述 : 将字节对象转化为字符串对象
	 * 
	 * @param
	 * @return String
	 * @since 1.0.0
	 * @Create Date: 2013-1-30 下午5:29:33
	 */
	public static String hexEncode(byte[] buffer, int size) {
		if (buffer.length == 0) {
			return "";
		}

		if (buffer.length < size) {
			size = buffer.length;
		}

		int holder = 0;
		char[] chars = new char[size * 2];
		for (int i = 0; i < size; i++) {
			holder = (buffer[i] & 0xf0) >> 4;
			chars[i * 2] = HEX[holder];
			holder = buffer[i] & 0x0f;
			chars[(i * 2) + 1] = HEX[holder];
		}
		return new String(chars);
	}

	/**
	 * 
	 * 方法名称 : hexDecode 方法描述 : 将字符串对象转化为字节对象
	 * 
	 * @param hex
	 * @return byte[]
	 * @since 1.0.0
	 * @Create Date: 2013-1-30 下午5:29:33
	 */
	public static byte[] hexDecode(String hex) {
		// A null string returns an empty array
		if (hex == null || hex.length() == 0) {
			return new byte[0];
		} else if (hex.length() < 3) {
			return new byte[] { (byte) (Integer.parseInt(hex, 16) & 0xff) };
		}
		// Adjust accordingly for odd-length strings
		int count = hex.length();
		int nibble = 0;
		if (count % 2 != 0) {
			count++;
			nibble = 1;
		}
		byte[] buf = new byte[count / 2];
		char c = 0;
		int holder = 0;
		int pos = 0;
		for (int i = 0; i < buf.length; i++) {
			for (int z = 0; z < 2 && pos < hex.length(); z++) {
				c = hex.charAt(pos++);
				if (c >= 'A' && c <= 'F') {
					c -= 55;
				} else if (c >= '0' && c <= '9') {
					c -= 48;
				} else if (c >= 'a' && c <= 'f') {
					c -= 87;
				}
				if (nibble == 0) {
					holder = c << 4;
				} else {
					holder |= c;
					buf[i] = (byte) holder;
				}
				nibble = 1 - nibble;
			}
		}
		return buf;
	}

	/**
	 * 
	 * 方法名称 : decode 方法描述 : 将nio 的ByteBuffer对象转为字符串对象
	 * 
	 * 
	 * @param buffer
	 * @param charset
	 * @return String
	 * 
	 * @since 1.0.0
	 * @Create Date: 2013-1-30 下午5:29:33
	 */
	public static String decode(ByteBuffer buffer, String charset) {
		Charset dcharset = null;
		CharsetDecoder decoder = null;
		CharBuffer charBuffer = null;
		try {
			dcharset = Charset.forName(charset);
			decoder = dcharset.newDecoder();
			charBuffer = decoder.decode(buffer);
			return charBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static byte[] intToByteArray1(int i) {
		byte[] result = new byte[2];
		result[0] = (byte) ((i >> 8) & 0xFF);
		result[1] = (byte) ((i & 0xFF));

		return result;
	}

	// public static byte[] intToByte(int i) {
	// byte[] result = new byte[4];
	//
	// result[0] = (byte) ((i >> 8) & 0xFF);
	// result[1] = (byte) ((i & 0xFF));
	//
	// result[2] = (byte) ((i) & 0xFF);
	// result[3] = (byte) ((i & 0xFF));
	// return result;
	// }

	public static byte[] intToByte(int i) {

		byte[] abyte0 = new byte[4];


		abyte0[3] = (byte) (0xff & i);

		abyte0[2] = (byte) ((0xff00 & i) >> 8);

		abyte0[1] = (byte) ((0xff0000 & i) >> 16);

		abyte0[0] = (byte) ((0xff000000 & i) >> 24);

		return abyte0;

	}

	
	
	public static byte[] intToByte2(int i) {

		byte[] abyte0 = new byte[4];


		abyte0[0] = (byte) (0xff & i);

		abyte0[1] = (byte) ((0xff00 & i) >> 8);

		abyte0[2] = (byte) ((0xff0000 & i) >> 16);

		abyte0[3] = (byte) ((0xff000000 & i) >> 24);

		return abyte0;

	}
	public static int stringToByte(String in, byte[] b) {
		if (b.length < in.length() / 2) {
			System.out.println("byte array too small");
			return -1;
		}
		int j=0;
		StringBuffer buf = new StringBuffer(2);
		for (int i=0; i<in.length(); i++, j++) {
			buf.insert(0, in.charAt(i));
			buf.insert(1, in.charAt(i+1));
			int t = Integer.parseInt(buf.toString(),16);
			b[j] = (byte)t;
			i++;
			buf.delete(0,2);
		}
		return j;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String test = StringUtil.stringtoHexAndFillZeroRight("北京理工大学附属中学", 19);
		System.out.println(test);
		System.out.println("length:"+test.length());
		System.out.println("============================");
		String content = "010000C6BDBCD2EEB600000000000000000000000000023C00C6BDBCD2EEB6C4CFBFDA000000000000000000039600B8DFB6FBB7F2C7F2B3A1000000000000000000046801C4DABEFCD7AF0000000000000000000000000005AE01D0A1D3AAB1B1BFDA000000000000000000000006989ED0A1D3AA000000000000000000000000000000071C02B1B1BEA9C0EDB9A4B4F3D1A7B8BDCAF4D6D000084E02D0A1C5D3D7AF00000000000000000000000000096C02D0ECD0C1D7AFBEB4C0CFD4BA000000000000000a8A02D0ECD0C1D7AFCEC0C9FAD4BA000000000000000bBC02D0ECD0C1D7AFD3CABED60000000000000000000cF802D0ECD0C1D7AFCAD0B3A10000000000000000000d3E03B1B1BEA9B5E7D7D3D0C5CFA2BCBCCAA6D1A7000e7003D0ECD0C1D7AF000000000000000000000000000f3804CBABB2BACDB7BCD3D3CDD5BE00000000000000109C04B8BBBAC0B1B1BFDA000000000000000000000011EC04B8BBBAC0C4CFBFDA0000000000000000000000126405B4E4B8A3D4B7D0A1C7F8000000000000000000139605B1B1C2EDD7AFC4CF000000000000000000000014DC05CEF7C2BAD4B0D0A1C7F8000000000000000000154A06B8BBBAD3D4B0D0A1C7F8000000000000000000168606BDE1D1D0CBF90000000000000000000000000017C206B2C6C3B3D1A7D4BA0000000000000000000000183007B0CBC0EFC7C5CAD0B3A1000000000000000000194407BDE2B7C5BEFCB6FEC1F9C8FDD2BDD4BACEF7001a7607CEF7C3C5C2B7BFDAB1B10000000000000000001b9E07CEF7C3C5C2B7BFDAC4CF0000000000000000001cBC07B8B4D0CBC0EFB1B1BFDA0000000000000000001dDA07B8B4D0CBC0EFCEF7BFDA0000000000000000001e2008B3C7CCFAB1B1D4B700000000000000000000001f3E08B1B1D4B7C2B7BFDACEF70000000000000000001f0000B1B1D4B7C2B7BFDACEF70000000000000000001e2800B3C7CCFAB1B1D4B700000000000000000000001d6400B8B4D0CBC0EFCEF7BFDA0000000000000000001c8200B8B4D0CBC0EFB1B1BFDA0000000000000000001bAA00CEF7C3C5C2B7BFDAC4CF0000000000000000001aC800CEF7C3C5C2B7BFDAB1B100000000000000000019FA00BDE2B7C5BEFCB6FEC1F9C8FDD2BDD4BACEF7BFDA182201B0CBC0EFC7C5CAD0B3A1000000000000000000179001B2C6C3B3D1A7D4BA000000000000000000000016C201BDE1D1D0CBF90000000000000000000000000015EA01B8BBBAD3D4B0D0A1C7F8000000000000000000148A02CEF7C2BAD4B0D0A1C7F800000000000000000013BC02B1B1C2EDD7AFC4CF0000000000000000000000120C03B4E4B8A3D4B7D0A1C7F8000000000000000000118E03B8BBBAC0C4CFBFDA000000000000000000000010DE03B8BBBAC0B1B1BFDA00000000000000000000000f4C04CBABB2BACDB7BCD3D3CDD5BE000000000000000e1405D0ECD0C1D7AF000000000000000000000000000d4605B1B1BEA9B5E7D7D3D0C5CFA2BCBCCAA6D1A7D4BA0c8C05D0ECD0C1D7AFCAD0B3A10000000000000000000bBE05D0ECD0C1D7AFD3CABED60000000000000000000aF005D0ECD0C1D7AFCEC0C9FAD4BA00000000000000090E06D0ECD0C1D7AFBEB4C0CFD4BA00000000000000082C06D0A1C5D3D7AF00000000000000000000000000075E06B1B1BEA9C0EDB9A4B4F3D1A7B8BDCAF4D6D0D1A706AE06D0A1D3AA00000000000000000000000000000005CC06D0A1D3AAB1B1BFDA0000000000000000000000041207C4DABEFCD7AF0000000000000000000000000003E407B8DFB6FBB7F2C7F2B3A1000000000000000000023E08C6BDBCD2EEB6C4CFBFDA000000000000000000017A08C6BDBCD2EEB600000000000000000000000000";
		for (int i = 0; i < 31; i++) {
			String tmp = content.substring(i*44+6,(i+1)*44).trim();
			tmp = tmp.replaceAll("00", "");
			System.out.println(StringUtil.hexToStringGBK(tmp));
		}
		
	}
}
