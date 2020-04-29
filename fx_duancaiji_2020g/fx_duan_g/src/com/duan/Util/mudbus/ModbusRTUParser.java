package com.duan.Util.mudbus;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import com.duan.Util.Hex2Float;
import com.duan.Util.IParser;
import com.duan.Util.LogUtil;

public class ModbusRTUParser implements IParser {

	@Override
	public double Parse(int startAddress, int length, int[] buffer, float factor, String IsBigEndian, String aa) {

		
		LogUtil.print("Parse " + length);
		
		// 原来拓远的计算方法是错误的，遇到负数或者某些正数无法正常解析，后改为尤工提供思路的移位算法，经测试，可以正常返回。//20171024 by dyf
		int[] data = new int[length * 2];
		long value = 0;
		float value111 = 0;
		short value1 = 0;
		int value2 = 0;
		if (length == 1) {
			data[0] = buffer[3];
			data[1] = buffer[4];
			// MyLog.write485(data[0]+"---"+data[1]+"");
			if (IsBigEndian.equals("0") || IsBigEndian.equals("2")) {// 0是高位在前
				// value = (data[0] * 256 + data[1]) ;
				value1 = (short) (data[0] << 8);
				// MyLog.write485("BB"+value1+"");
				value1 |= (data[1] & 0x000000ff);
				// MyLog.write485("CC"+value1+"");
			} else if (IsBigEndian.equals("1")) {// 1是高位在后
				// value = (data[1] * 256 + data[0]) ;
				value1 = (short) (data[1] << 8);
				value1 |= (data[0] & 0x000000ff);
			}
			value = (long) (value1);
		} else if (length == 2) {
			data[0] = buffer[3];
			data[1] = buffer[4];
			data[2] = buffer[5];
			data[3] = buffer[6];
			LogUtil.print("Parse " + data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
			
			// 2是新增的 DTS FX03电表 双字节时低位在前
			if (IsBigEndian.equals("0")) {
				// value = ((data[0] * 256 + data[1]) * 65536 + (data[2] * 256 + data[3]));
				if (aa.equals("1")) {
					byte[] data11 = new byte[4];

					data11[0] = (byte) data[0];
					data11[1] = (byte) data[1];
					data11[2] = (byte) data[2];
					data11[3] = (byte) data[3];

//								float value11 = 0;
//								if (IsBigEndian.equals("0")) {// 高位在前
//									data11[0] = (byte) data[0];
//									data11[1] = (byte) data[1];
//									data11[2] = (byte) data[2];
//									data11[3] = (byte) data[3];
//								} else if (IsBigEndian.equals("1")) {
//									// value = ((data[3] * 256 + data[2]) * 256 + (data[1] * 256 +
//									// data[0])) * factor;
//								}
				 
					DataInputStream dis11 = new DataInputStream(new ByteArrayInputStream(data11));
					try {
						 value111 = Hex2Float.byte2float(data11,0);
						//value111 = dis11.readFloat();
						dis11.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					 //		return value111 * factor;
					 double fvalue=new  BigDecimal(Float.toString(value111 )).multiply(new  BigDecimal(Float.toString( factor))).doubleValue();
					 return  fvalue;
				
				} else {
					value2 = data[0] << 8;
					value2 |= (data[1] & 0x000000ff);
					value2 <<= 8;
					value2 |= (data[2] & 0x000000ff);
					value2 <<= 8;
					value2 |= (data[3] & 0x000000ff);
				}

			} else if (IsBigEndian.equals("1")) {
				// value = ((data[3] * 256 + data[2]) * 65536 + (data[1] * 256 + data[0]));
				value2 = data[3] << 8;
				value2 |= (data[2] & 0x000000ff);
				value2 <<= 8;
				value2 |= (data[1] & 0x000000ff);
				value2 <<= 8;
				value2 |= (data[0] & 0x000000ff);
			} else if (IsBigEndian.equals("2")) {
				// value = ((data[2] * 256 + data[3]) * 65536 + (data[0] * 256 + data[1]));
				value2 = data[2] << 8;
				value2 |= (data[3] & 0x000000ff);
				value2 <<= 8;
				value2 |= (data[0] & 0x000000ff);
				value2 <<= 8;
				value2 |= (data[1] & 0x000000ff);
			}
			value = (long) (value2);
		}
		/*
		 * int s=0; for(int i=0;i<tmpByte.length;i++){ s<<=8; s|=(tmpByte[i] &
		 * 0x000000ff); } return s;
		 */
		// 这里是因为有些设备是容性负载，会采到负数，比如功率因数和功率，以前不取负数，这里改掉 20170708 dyf
		/*
		 * if ((startAddress == 790) || (startAddress == 791) || (startAddress == 792)
		 * || (startAddress == 802) || (startAddress == 775) || (startAddress == 776) ||
		 * (startAddress == 777) || (startAddress == 780) || (startAddress == 781) ||
		 * (startAddress == 782)) { if (value > 32767) { value = ~value + 1; value =
		 * (value & 0xFFFF) * -1; } }
		 */
		/*
		 * if ((startAddress == 114) || (startAddress == 115) || (startAddress == 116)
		 * || (startAddress == 117) || (startAddress == 118) || (startAddress == 120) ||
		 * (startAddress == 122) || (startAddress == 124) || (startAddress == 126) ||
		 * (startAddress == 127) || (startAddress == 128) || (startAddress == 129)) { if
		 * (((value > 32767) && (length == 1)) || ((value > 2147483647) && (length ==
		 * 2))) { value = ~value + 1; value = (value & 0xFFFF) * -1; } }
		 */
		/*
		 * if (value1 <0){ value1 = (short) ((value1 & 0xFFFF)); }
		 */
		// MyLog.write485("AA" + value1 * factor +"");
		return new  BigDecimal(Float.toString(value )).multiply(new  BigDecimal(Float.toString( factor))).doubleValue();
		//return value * factor;

	}

}
