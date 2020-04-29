package com.duan.Util.x645;

import com.duan.Util.DataHandleTool;

public class DTL64507 {

	/// <summary>
		/// 创建消息。
		/// </summary>
		/// <param name="deviceId">设备 ID 。</param>
		/// <returns>byte[] 类型数据。</returns>
		public static int[] buildMessage07(long deviceId, long address) {
			// 645 97 协议格式 FE FE FE 68 36 00 00 00 00 00 68 11 04 33 33 33 33 E7 16
			
//			FEFEFEFE 68 042109010000 68 1104 020101001816
//			FEFEFEFE68042109010000681104 33343435E416
			String meterAddress = DataHandleTool.addZeroForNum(deviceId + "", 12);
			int[] message = new int[20];
			message[0] = 254;
			message[1] = 254;
			message[2] = 254;
			message[3] = 254;
			message[4] = 104;
			message[5] = Integer.parseInt(meterAddress.substring(10, 12), 16);
			message[6] = Integer.parseInt(meterAddress.substring(8, 10), 16);
			message[7] = Integer.parseInt(meterAddress.substring(6, 8), 16);
			message[8] = Integer.parseInt(meterAddress.substring(4, 6), 16);
			message[9] = Integer.parseInt(meterAddress.substring(2, 4), 16);
			message[10] = Integer.parseInt(meterAddress.substring(0, 2), 16);
			message[11] = 104;
			message[12] = 17;
			message[13] = 4;
//			int data1 = address % 256 + 0x33;
//			int data2 = address / 256 + 0x33;
//			int data3 = address / 65536 + 0x33;
//			int data4 = address / 16777216 + 0x33;
			long data1 = address % 256 + 0x33;
			long data2 = address / 256 + 0x33;
			long data3 = address / 65536 + 0x33;
			long data4 = address / 16777216 + 0x33;
			message[14] = (byte) (data1 & 0xff);
			message[15] = (byte) (data2 & 0xff);
			message[16] = (byte) (data3 & 0xff);
			message[17] = (byte) (data4 & 0xff);
//			Log.v("nhm ", " buildMessage1 "+address+"  "+data1+"  "+data2+"  "+data3+"  "+data4);
			int sum = 0;
//			[254, 254, 254, 254, 104, 4, 33, 9, 1, 0, 0, 104, 17, 4, 51, 53, 57, 53, 0, 0]
//			104, 4, 33, 9, 1, 0, 0, 104, 17, 4, 51, 54, 56, 53, 22, 0]
			for (int i = 4; i < 18; i++) {
				sum += message[i];
			}
//			message[18] = (byte)(sum % 256);
			message[18] = (byte) (sum & 0xff);
//			message[18] =  (byte) (sum % 0x100);
//			if(message[18]>0){
//				
//			}else{
//				
//				message[18] = (byte)(0x100-sum & 0xff);
//			}
//			Log.v("nhm ", " buildMessage1 "+sum+"  "+message[18]);
			message[19] = 22;         
			return message;
		}

		public static int[] getBuffer07(int[] buffer, int length) {
			int[] returnbuffer = new int[] { 0, 0, 0, 0 };
			if (length == 22) {
				returnbuffer[0] = buffer[18] - 0x33;
				returnbuffer[1] = buffer[19] - 0x33;
			} else if (length == 23) {
				returnbuffer[0] = buffer[18] - 0x33;
				returnbuffer[1] = buffer[19] - 0x33;
				returnbuffer[2] = buffer[20] - 0x33;
			} else if (length == 24) {
				returnbuffer[0] = buffer[18] - 0x33;
				returnbuffer[1] = buffer[19] - 0x33;
				returnbuffer[2] = buffer[20] - 0x33;
				returnbuffer[3] = buffer[21] - 0x33;
			}
//			else if (length == 37) {
//				returnbuffer = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };// 22个
//				returnbuffer[0] = buffer[13] - 0x33;
//				returnbuffer[1] = buffer[14] - 0x33;
//				returnbuffer[2] = buffer[15] - 0x33;
//				returnbuffer[3] = buffer[16] - 0x33;
//				returnbuffer[4] = buffer[17] - 0x33;
//				returnbuffer[5] = buffer[18] - 0x33;
//				returnbuffer[6] = buffer[19] - 0x33;
//				returnbuffer[7] = buffer[20] - 0x33;
//				returnbuffer[8] = buffer[21] - 0x33;
//				returnbuffer[9] = buffer[22] - 0x33;
//				returnbuffer[10] = buffer[23] - 0x33;
//				returnbuffer[11] = buffer[24] - 0x33;
//				returnbuffer[12] = buffer[25] - 0x33;
//				returnbuffer[13] = buffer[26] - 0x33;
//				returnbuffer[14] = buffer[27] - 0x33;
//				returnbuffer[15] = buffer[28] - 0x33;
//				returnbuffer[16] = buffer[29] - 0x33;
//				returnbuffer[17] = buffer[30] - 0x33;
//				returnbuffer[18] = buffer[31] - 0x33;
//				returnbuffer[19] = buffer[32] - 0x33;
//				returnbuffer[20] = buffer[33] - 0x33;
//				returnbuffer[21] = buffer[34] - 0x33;
//			}
			return returnbuffer;

		}

		/// <summary>
		/// 验证数据缓存。
		/// </summary>
		/// <param name="message">消息。</param>
		/// <param name="tmpResponse"> </param>
		/// <param name="buffer">数据缓存。</param>
		/// <returns>true 表示数据有效， false 表示数据无效。</returns>
		public static boolean ValidateBuffer07(int[] message, int[] buffer) {
//			Log.v("nhm ", " ValidateBuffer 645-07   11");
			if (ClearWasionBtye07(message, buffer)) {
//				Log.v("nhm ", " ValidateBuffer 645-07  22");
				if (CheckWasion645DLTRead(buffer)) {
//					Log.v("nhm ", " ValidateBuffer  645-07 33");
					return true;
				}
			}
			return false;
		}

		private static boolean ClearWasionBtye07(int[] message, int[] response) {
			boolean check = false;
//			FE FE FE 68 36 00 00 00 00 00 68 11 04 33 33 33 33 E7 16
			//FEFEFEFE 68042109010000681104 33343435E416
//			[254, 254, 254, 254, 104, 4, 33, 9, 1, 0, 0, 104, 17, 4, 51, 54, 56, 53, -22, 22]
//			[254, 254, 254, 254, 104, 4, 33, 9, 1, 0, 0, 104, 209, 1, 53, 6, 22, 0, 0, 0, 0, 0, 0]
			
//			[254, 254, 254, 254, 104, 4, 33, 9, 1, 0, 0, 104, 145, 6, 51, 52, 52, 53, 71, 85, 2, 22]
			
//			for (int ij = 0; ij < message.length; ij++) {
//				System.out.print(message[ij]+" ");
//			}
//			System.out.println("");
//			for (int ij = 0; ij < message.length; ij++) {
//				String strHex = Integer.toHexString(message[ij]);
//				System.out.print(strHex+" ");
//			}
//			System.out.println("");
//			for (int ij = 0; ij < response.length; ij++) {
//				System.out.print(response[ij]+" ");
//			}
//			System.out.println("");
			if (response[4] == 104) {
				int j;
				for (j = 4; j < 11; j++) {
					if (response[j] != message[j])
						break;
				}
			 
				if (response.length == 22) {
					if (j == 11 && response[11] == 104 && response[12] == 145 && response[13] == 06 && response[21] == 22) {
						check = true;
					}
				} else if (response.length == 23) {
					if (j == 11 && response[11] == 104 && response[12] == 145 && response[13] == 07 && response[22] == 22) {
						check = true;
					}
				} else if (response.length == 24) {
					if (j == 11 && response[11] == 104 && response[12] == 145 && response[13] == 8 && response[23] == 22) {
						check = true;
					}
				} //这里修改收回的数据个数为37  ，以及校验的返回数据是否正确问题  20170708  dyf
				  else if (response.length == 37) {	
					if (j == 11 && response[11] == 104 && response[12] == 145 && response[13] == 24 && response[36] == 22) {
						check = true;						
					}
				}
			}
 
			return check;
		}

		private static boolean CheckWasion645DLTRead(int[] response) {
			String crc = Get645CRCDLT(response);
			String chk = Integer.toHexString(response[response.length - 2]);
 
			if (!crc.equals(chk)) {
				return false;
			}
			return true;
		}

		private static String Get645CRCDLT(int[] response) {
			String crc = "";

			int sum = 0;

			for (int i = 4; i < response.length - 2; i++) {
				sum += response[i];
			}
//			crc = Integer.toHexString(sum % 0x100);
			crc = Integer.toHexString(sum % 256);
 
			return crc;
		}
 
}
