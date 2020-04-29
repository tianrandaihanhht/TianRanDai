package com.duan.Util;

public class StringUtil {

	public static  String Byte2HexString(byte[] byteArray) {
		if (byteArray == null || byteArray.length < 1) {
			LogUtil.print("转换数据为空！"); 
		}

		final StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			if ((byteArray[i] & 0xff) < 0x10)// 0~F前面不零
			{
				hexString.append("0");
			}
			hexString.append(Integer.toHexString(0xFF & byteArray[i])).append(" ");
		}
		return hexString.toString().toUpperCase();
	}

	public static byte[] hexStr2ByteStr(String hexString) {
		hexString = hexString.replaceAll(" ", "");
		int len = hexString.length();
		int index = 0;
		byte[] bytes = new byte[len / 2];
		while (index < len) {
			String sub = hexString.substring(index, index + 2);
			bytes[index / 2] = (byte) Integer.parseInt(sub, 16);
			index += 2;
		}
		return bytes;
	}
}
