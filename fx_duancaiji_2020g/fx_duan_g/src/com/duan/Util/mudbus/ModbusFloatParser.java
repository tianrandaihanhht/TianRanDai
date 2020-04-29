package com.duan.Util.mudbus;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.duan.Util.IParser;

public class ModbusFloatParser implements IParser {
	@Override
	public double Parse(int startAddress, int length, int[] buffer, float factor, String IsBigEndian, String aa) {

		// byte[] data = new byte[] { 0x43, 0x02, 0x00, 0x00 };
		byte[] data = new byte[4];
		float value = 0;
		if (length == 2) {

			if (IsBigEndian.equals("0")) {// 高位在前
				data[0] = (byte) buffer[3];
				data[1] = (byte) buffer[4];
				data[2] = (byte) buffer[5];
				data[3] = (byte) buffer[6];
			} else if (IsBigEndian.equals("1")) {
				// value = ((data[3] * 256 + data[2]) * 256 + (data[1] * 256 +
				// data[0])) * factor;
			}
		}
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		try {
			value = dis.readFloat();
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return value * factor;
	}

}
