package com.duan.Util.mudbus;

import java.util.Calendar;



public class Modbus_android {

	// private int meterAddress = 255;// 电表地址

	// / <summary>
	// / 创建消息。
	// / </summary>
	// / <param name="address">寄存器地址。</param>
	// / <param name="length">存器长度。</param>
	// / <returns>byte[] 类型数据。</returns>
	public static byte[] buildMessage(int meteraddress, int address, int length, int code) {
		byte[] message = new byte[8];
		message[0] = (byte) meteraddress;
		message[1] = (byte) code;
		message[2] = (byte) (address >> 8);
		message[3] = (byte) address;
		message[4] = (byte) (length >> 8);
		message[5] = (byte) length;

		int[] message1 = new int[8];
		message1[0] = (int) meteraddress;
		message1[1] = 3;
		message1[2] = (int) (address >> 8);
		if (address >= 1023) {
			if ((byte) address < 0) {
				message1[3] = (int) (256 + (byte) address);
			} else {
				message1[3] = (byte) address;
			}

		} else if (address > 256) {
			message1[3] = (short) (address - 256);
		} else {
			message1[3] = (int) (address);
		}

		message1[4] = (int) (length >> 8);
		message1[5] = (int) length;

		byte[] crc = ComputeCRC111(message1);
		message[6] = crc[0];
		message[7] = crc[1];
		return message;
	}

	// / <summary>
	// / 创建消息。
	// / </summary>
	// / <param name="address">寄存器地址。</param>
	// / <param name="length">存器长度。</param>
	// / <returns>byte[] 类型数据。</returns>
	/////////public static int[] buildMessage11(int meteraddress, int address, int length, int code) {
	public static int[] buildMessage11(int meteraddress, int address, int length, int code) {
		int[] message = new int[8];
		//message[0] = (byte) meteraddress;
		//message[1] = code;
		//message[2] = (byte) (address >> 8);
		//message[3] = (byte) address;
		message[0] = meteraddress;
		message[1] = code;
		message[2] = address >> 8;
		message[3] = address;
		if (address >= 1023) {
			if ((byte) address < 0) {
				message[3] = (int) (256 + (byte) address);
			} else {
				message[3] = (byte) address;
			}

		} else if (address > 256) {
			message[3] = (short) (address - 256);
		} else {
			message[3] = (int) (address);
		}

		message[4] = (byte) (length >> 8);
		message[5] = (byte) length;
		message[0] = message[0] % 256;    //这里解决校验码 校验发出不正确的问题  20170708  dyf
	    message[1] = message[1] % 256;
	    message[2] = message[2] % 256;
	    message[3] = message[3] % 256;
	    message[4] = message[4] % 256;
	    message[5] = message[5] % 256;
		byte[] crc = ComputeCRC111(message);

		message[6] = crc[0];
		message[7] = crc[1];

		return message;
	}

	public byte[] buildMeterDataMessage(int deviceId, int mess, short address) {
		byte[] message = new byte[11];
		message[0] = (byte) deviceId;
		message[1] = 16;

		message[2] = (byte) (address >> 8);
		message[3] = (byte) address;

		message[4] = 0;
		message[5] = 1;

		message[6] = 2;

		message[7] = (byte) (mess >> 8);
		message[8] = (byte) mess;

		short[] message1 = new short[11];
		message1[0] = (short) deviceId;
		message1[1] = 16;

		message1[2] = (short) (address >> 8);
		message1[3] = (short) address;
		if (address >= 1023) {
			if ((byte) address < 0) {
				message1[3] = (short) (256 + (byte) address);
			} else {
				message1[3] = (byte) address;
			}

		} else if (address > 256) {
			message1[3] = (short) (address - 256);
		} else {
			message1[3] = (short) (address);
		}

		message1[4] = 0;
		message1[5] = 1;

		message1[6] = 2;

		message1[7] = (short) (mess >> 8);
		if (mess >= 256) {
			message1[8] = (short) (mess - 256);
		} else {
			message1[8] = (short) (mess);
		}

		byte[] crc = ComputeCRC(message1);

		message[9] = crc[0];
		message[10] = crc[1];
		return message;
	}

	public byte[] buildMeterTimeMessage(int deviceId, short address) {
		// 62 10 00 CF 00 04 08 2A 1C 09 03 03 0F 00 02 28 BF
		byte[] message = new byte[17];
		message[0] = (byte) deviceId;
		message[1] = 16;

		message[2] = (byte) (address >> 8);
		message[3] = (byte) address;
		message[4] = 0;
		message[5] = 4;
		message[6] = 8;

		Calendar calendar = Calendar.getInstance();
		message[7] = (byte) (0);// 秒
		message[8] = (byte) calendar.get(Calendar.MINUTE);// 分

		message[9] = (byte) calendar.get(Calendar.HOUR_OF_DAY);// 时
		message[10] = (byte) calendar.get(Calendar.DAY_OF_MONTH);// 天
		message[11] = (byte) (calendar.get(Calendar.MONTH) + 1);// 月

		String year = calendar.get(Calendar.YEAR) + "";

		year = year.substring(2, 4);
		message[12] = Byte.parseByte(year);// 年

		message[13] = (byte) 0;
		message[14] = (byte) (calendar.get(Calendar.DAY_OF_WEEK) - 1);// 星期 0-6
		// Log.d("测试", calendar.get(Calendar.DAY_OF_WEEK)+"");

		short[] message1 = new short[17];
		message1[0] = (short) deviceId;
		message1[1] = 16;

		message1[2] = (short) (address >> 8);
		message1[3] = (short) address;
		if (address >= 1023) {
			if ((byte) address < 0) {
				message1[3] = (short) (256 + (byte) address);
			} else {
				message1[3] = (byte) address;
			}

		} else if (address > 256) {
			message1[3] = (short) (address - 256);
		} else {
			message1[3] = (short) (address);
		}
		message1[4] = 0;
		message1[5] = 4;
		message1[6] = 8;
		for (int i = 7; i < message.length - 2; i++) {
			message1[i] = message[i];
		}

		byte[] crc = ComputeCRC(message1);
		message[15] = crc[0];
		message[16] = crc[1];

		return message;
	}

	public byte[] buildMeterNameMessage(int deviceId, short address, byte[] values) {
		byte[] message = new byte[17];
		message[0] = (byte) deviceId;
		message[1] = 16;

		message[2] = (byte) (address >> 8);
		message[3] = (byte) address;
		message[4] = 0;
		message[5] = 4;
		message[6] = 8;

		message[7] = values[0];
		message[8] = values[1];//
		message[9] = values[2];//
		message[10] = values[3];//
		message[11] = values[4];
		message[12] = values[5];//
		message[13] = values[6];//
		message[14] = values[7];//

		short[] message1 = new short[17];
		message1[0] = (short) deviceId;
		message1[1] = 16;

		message1[2] = (short) (address >> 8);
		message1[3] = (short) address;
		if (address >= 1023) {
			if ((byte) address < 0) {
				message1[3] = (short) (256 + (byte) address);
			} else {
				message1[3] = (byte) address;
			}

		} else {
			if (address >= 256) {
				message1[3] = (short) (address - 256);

			} else {
				message1[3] = (short) (address);
			}

		}
		message1[4] = 0;
		message1[5] = 4;
		message1[6] = 8;
		for (int i = 7; i < message.length - 2; i++) {
			message1[i] = message[i];
		}
		
		byte[] crc = ComputeCRC(message1);
		message[15] = crc[0];
		message[16] = crc[1];

		return message;
	}

	private byte[] ComputeCRC(short[] message) {
		short crcFull = (short) 0xFFFF;

		for (int i = 0; i < (message.length) - 2; i++) {
			crcFull = (short) (crcFull ^ message[i]);

			for (int j = 0; j < 8; j++) {
				short lsb = (short) (crcFull & 0x0001);
				crcFull = (short) ((crcFull >> 1) & 0x7FFF);

				if (lsb == 1)
					crcFull = (short) (crcFull ^ 0xA001);
			}
		}

		byte[] crc = new byte[2];
		crc[0] = (byte) (crcFull & 0xFF);
		crc[1] = (byte) ((crcFull >> 8) & 0xFF);
		return crc;
	}

	private static byte[] ComputeCRC111(int[] message) {
		//{88,03,03,26,00,02};
       	int crcFull = (int) 0xFFFF;
		for (int i = 0; i < (message.length) - 2; i++)
		{
			crcFull = (int) (crcFull ^ message[i]);
			for (int j = 0; j < 8; j++) {
				int lsb = (int) (crcFull & 0x0001);
				crcFull = (int) ((crcFull >> 1) & 0x7FFF);

				if (lsb == 1)
					crcFull = (int) (crcFull ^ 0xA001);
			}
		}
		byte[] crc = new byte[2];
		crc[0] = (byte) (crcFull & 0xFF);
		crc[1] = (byte) ((crcFull >> 8) & 0xFF);
		return crc;
	}

	// / <summary>
	// / 验证数据缓存。
	// / </summary>
	// / <param name="message">消息。</param>
	// / <param name="buffer">数据缓存。</param>
	// / <returns>true 表示数据有效， false 表示数据无效。</returns>
	public static boolean ValidateBuffer(int[] buffer, int code) {
		try {
			byte[] crc = ComputeCRC111(buffer);
			int length = buffer.length;
			int[] icrc = new int[2];
			if (buffer[1] != code) {
				return false;
			}
			if (buffer[2] == 0) {
				return false;
			}

			if (crc[0] < 0) {
				icrc[0] = (crc[0] + 256);
			} else {
				icrc[0] = (crc[0]);
			}
			if (crc[1] < 0) {
				icrc[1] = (crc[1] + 256);
			} else {
				icrc[1] = (crc[1]);
			}
			return icrc[0] == buffer[length - 2] && icrc[1] == buffer[length - 1];
		} catch (Exception err) {
			//ValidateBuffer(buffer, code);
			return false;
		}

	}

}
