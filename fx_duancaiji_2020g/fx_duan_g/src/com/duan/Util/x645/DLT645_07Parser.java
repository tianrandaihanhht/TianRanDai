package com.duan.Util.x645;

 
import java.math.BigDecimal;

import com.duan.Util.IParser;
import com.duan.Util.StringUtil;

public class DLT645_07Parser implements IParser {

	@Override
	public double Parse(int startAddress, int length, int[] buffer, float factor, String IsBigEndian, String aa) {

 
		float value111 = 0;
		length = (int) buffer[13];
		byte[] data11 = new byte[length - 4];

		if (IsBigEndian.equals("0")|| IsBigEndian.equals("2")) {
			int flag = data11.length - 1;

			for (int i = (buffer.length - (length-2)); i <= buffer.length - 3; i++) {
				data11[flag] = (byte) (buffer[i] - 0x33);
				flag--;
			}
		}else {
			int flag =0;
			for (int i = (buffer.length - (length)); i <= buffer.length - 3; i++) {
				data11[flag] = (byte) (buffer[i] - 0x33);
				flag++;
			}
		}
		String a = StringUtil.Byte2HexString(data11);
		value111 = Integer.parseInt(a.replace(" ", ""));
		
		 double fvalue=new  BigDecimal(Float.toString(value111 )).multiply(new  BigDecimal(Float.toString( factor))).doubleValue();
		// return new BigDecimal(Float.toString(value111 * factor)).doubleValue();
	    return fvalue;
	}

}
