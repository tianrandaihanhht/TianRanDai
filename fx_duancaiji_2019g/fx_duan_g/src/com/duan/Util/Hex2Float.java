package com.duan.Util;
/**java实现IEEE 754标准
 * 16进制转float
 * @author wp
 *
 */
public class Hex2Float {
	
	
	public static float byte2float(byte[] b, int index) {    
	    int l;                                             
	    l = b[index + 0];                                  
	    l &= 0xff;                                         
	    l |= ((long) b[index + 1] << 8);                   
	    l &= 0xffff;                                       
	    l |= ((long) b[index + 2] << 16);                  
	    l &= 0xffffff;                                     
	    l |= ((long) b[index + 3] << 24);                  
	    return Float.intBitsToFloat(l);                    
	}  
	
	/**
	 * 字节数组转float
	 * 采用IEEE 754标准
	 * @param bytes
	 * @return
	 */
	public static float bytes2Float(byte[] bytes){
		//获取 字节数组转化成的2进制字符串
		String BinaryStr = bytes2BinaryStr(bytes);
		//符号位S
		Long s = Long.parseLong(BinaryStr.substring(0, 1));
		//指数位E
		Long e = Long.parseLong(BinaryStr.substring(1, 9),2);
		//位数M
		String M = BinaryStr.substring(9);
		float m = 0,a,b;
		for(int i=0;i<M.length();i++){
			a = Integer.valueOf(M.charAt(i)+"");
			b = (float) Math.pow(2, i+1);
			m =m + (a/b);
		}
		Float f = (float) ((Math.pow(-1, s)) * (1+m) * (Math.pow(2,(e-127))));
		return f;
	}
	/**
	 * 将字节数组转换成2进制字符串
	 * @param bytes
	 * @return
	 */
	public static String bytes2BinaryStr(byte[] bytes){
		StringBuffer binaryStr = new StringBuffer();
		for(int i=0;i<bytes.length;i++){
			String str = Integer.toBinaryString((bytes[i] & 0xFF) + 0x100).substring(1);
			binaryStr.append(str);
		}
		return binaryStr.toString();
	}
	
 
 
	public static long parseLong(String s, int radix) throws NumberFormatException {
		if (s == null) {
			throw new NumberFormatException("null");
		}
 
		if (radix < Character.MIN_RADIX) {
			throw new NumberFormatException("radix " + radix + " less than Character.MIN_RADIX");
		}
		if (radix > Character.MAX_RADIX) {
			throw new NumberFormatException("radix " + radix + " greater than Character.MAX_RADIX");
		}
 
		long result = 0;
		boolean negative = false;
		int i = 0, len = s.length();
		long limit = -Long.MAX_VALUE;
		long multmin;
		int digit;
 
		if (len > 0) {
			char firstChar = s.charAt(0);
			if (firstChar < '0') { // Possible leading "+" or "-"
				if (firstChar == '-') {
					negative = true;
					limit = Long.MIN_VALUE;
				} else if (firstChar != '+')
					throw NumberFormatException.forInputString(s);
 
				if (len == 1) // Cannot have lone "+" or "-"
					throw NumberFormatException.forInputString(s);
				i++;
			}
			multmin = limit / radix;
			while (i < len) {
				// Accumulating negatively avoids surprises near MAX_VALUE
				digit = Character.digit(s.charAt(i++), radix);
				if (digit < 0) {
					throw NumberFormatException.forInputString(s);
				}
				if (result < multmin) {
					throw NumberFormatException.forInputString(s);
				}
				result *= radix;
				if (result < limit + digit) {
					throw NumberFormatException.forInputString(s);
				}
				result -= digit;
			}
		} else {
			throw NumberFormatException.forInputString(s);
		}
		return negative ? result : -result;
	}
}
 
class NumberFormatException extends IllegalArgumentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	public NumberFormatException(String s) {
		super(s);
	}
 
	static NumberFormatException forInputString(String s) {
		return new NumberFormatException("For input string: \"" + s + "\"");
	}
}

