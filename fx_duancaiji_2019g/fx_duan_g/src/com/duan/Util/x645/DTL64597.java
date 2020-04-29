package com.duan.Util.x645;

 

public class DTL64597 {

	/// <summary>
		/// 创建消息。
		/// </summary>
		/// <param name="deviceId">设备 ID 。</param>
		/// <returns>byte[] 类型数据。</returns>
		public static int[] buildMessage1(long deviceId, int address) {
			// 645 97 协议格式 FE FE FE 68 36 00 00 00 00 00 68 11 04 33 33 33 33 E7 16
//			String meterAddress = DataHandleTool.addZeroForNum(deviceId + "", 12);
			String meterAddress = String.format("%0"+12+"d", deviceId);
//			MyLog.write485("NN55" + 1);
			int[] message = new int[17];
			message[0] = 254;
			message[1] = 254;
			message[2] = 254;
			message[3] = 104;
//			MyLog.write485("NN55" + 2+" "+meterAddress);
//			message[4] = (int) (deviceId%100);
//			Log.v("NN    55",message[4]+"");
			message[4] = Integer.parseInt(meterAddress.substring(10, 12), 16);
//			Log.v("NN    55",message[4]+"");
//			MyLog.write485("NN55" + 21+" "+message[4]);
//			message[5] = (int) ((deviceId/100)%100);
//			Log.v("NN    55",message[5]+"");
			message[5] = Integer.parseInt(meterAddress.substring(8, 10), 16);
//			Log.v("NN    55",message[5]+"");
//			MyLog.write485("NN55" + 22+" "+message[5]);
//			message[6] = (int) ((deviceId/10000)%100);
//			Log.v("NN    55",message[6]+"");
			message[6] = Integer.parseInt(meterAddress.substring(6, 8), 16);
//			Log.v("NN    55",message[6]+"");
//			MyLog.write485("NN55" + 23+" "+message[6]);
//			message[7] = (int) ((deviceId/1000000)%100);
//			Log.v("NN    55",message[7]+"");
			message[7] = Integer.parseInt(meterAddress.substring(4, 6), 16);
//			Log.v("NN    55",message[7]+"");
//			MyLog.write485("NN55" + 24+" "+message[7]);
//			message[8] = (int) ((deviceId/100000000)%100);
//			Log.v("NN    55",message[8]+"");
			message[8] = Integer.parseInt(meterAddress.substring(2, 4), 16);
//			Log.v("NN    55",message[8]+"");
//			MyLog.write485("NN55" + 25+" "+message[8]);
//			message[9] = (int) ((deviceId/10000000000l)%100);
//			Log.v("NN    55",message[9]+"");
			message[9] = Integer.parseInt(meterAddress.substring(0, 2), 16);
//			Log.v("NN    55",message[9]+"");
//			MyLog.write485("NN55" + 26+" "+message[9]);
//			MyLog.write485("NN55" + 3);
			message[10] = 104;
			message[11] = 01;
			message[12] = 02;
			int data1 = address % 256 + 0x33;
			int data2 = address / 256 + 0x33;
			message[13] = (byte) (data1);
			message[14] = (byte) (data2);
			int sum = 0;
			for (int i = 3; i < 15; i++) {
				sum += message[i];
			}
//			message[15] = (byte) (sum % 0x100); 
			message[15] = (byte) (sum & 0xff);
			message[16] = 22;         
			return message;
		}

		public static int[] getBuffer(int[] buffer, int length) {
			int[] returnbuffer = new int[] { 0, 0, 0, 0 };
			if (length == 17) {
				returnbuffer[0] = buffer[13] - 0X33;
				returnbuffer[1] = buffer[14] - 0X33;
			} else if (length == 18) {

				returnbuffer[0] = buffer[13] - 0X33;
				returnbuffer[1] = buffer[14] - 0X33;
				returnbuffer[2] = buffer[15] - 0X33;
			} else if (length == 19) {
				returnbuffer[0] = buffer[13] - 0X33;
				returnbuffer[1] = buffer[14] - 0X33;
				returnbuffer[2] = buffer[15] - 0X33;
				returnbuffer[3] = buffer[16] - 0X33;
			}
			else if (length == 37) {
				returnbuffer = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };// 22个
				returnbuffer[0] = buffer[13] - 0x33;
				returnbuffer[1] = buffer[14] - 0x33;
				returnbuffer[2] = buffer[15] - 0x33;
				returnbuffer[3] = buffer[16] - 0x33;
				returnbuffer[4] = buffer[17] - 0x33;
				returnbuffer[5] = buffer[18] - 0x33;
				returnbuffer[6] = buffer[19] - 0x33;
				returnbuffer[7] = buffer[20] - 0x33;
				returnbuffer[8] = buffer[21] - 0x33;
				returnbuffer[9] = buffer[22] - 0x33;
				returnbuffer[10] = buffer[23] - 0x33;
				returnbuffer[11] = buffer[24] - 0x33;
				returnbuffer[12] = buffer[25] - 0x33;
				returnbuffer[13] = buffer[26] - 0x33;
				returnbuffer[14] = buffer[27] - 0x33;
				returnbuffer[15] = buffer[28] - 0x33;
				returnbuffer[16] = buffer[29] - 0x33;
				returnbuffer[17] = buffer[30] - 0x33;
				returnbuffer[18] = buffer[31] - 0x33;
				returnbuffer[19] = buffer[32] - 0x33;
				returnbuffer[20] = buffer[33] - 0x33;
				returnbuffer[21] = buffer[34] - 0x33;
			}
			return returnbuffer;

		}

		/// <summary>
		/// 验证数据缓存。
		/// </summary>
		/// <param name="message">消息。</param>
		/// <param name="tmpResponse"> </param>
		/// <param name="buffer">数据缓存。</param>
		/// <returns>true 表示数据有效， false 表示数据无效。</returns>
		public static boolean ValidateBuffer(int[] message, int[] buffer) {

			if (ClearWasionBtye(message, buffer)) {

				if (CheckWasion645DLTRead(buffer)) {

					return true;
				}
			}

			return false;

		}

		private static boolean ClearWasionBtye(int[] message, int[] response) {
			boolean check = false;
//			for (int ij = 0; ij < message.length; ij++) {
//				System.out.print(message[ij]+" ");
//			}
//			System.out.println("");
//			for (int ij = 0; ij < response.length; ij++) {
//				System.out.print(response[ij]+" ");
//			}
//			System.out.println("");
			if (response[1] == 104) {
				int j;
				for (j = 1; j < 8; j++) {
					if (response[j] != message[2 + j])
						break;
				}
				
				if (response.length == 19) {
					if (j == 8 && response[8] == 104 && response[9] == 129 && response[10] == 06 && response[18] == 22) {
						check = true;

					}
				} else if (response.length == 18) {
					if (j == 8 && response[8] == 104 && response[9] == 129 && response[10] == 05 && response[17] == 22) {
						check = true;

					}
				} else if (response.length == 17) {
					if (j == 8 && response[8] == 104 && response[9] == 129 && response[10] == 04 && response[16] == 22) {
						check = true;
					}
				
				} //这里修改收回的数据个数为37  ，以及校验的返回数据是否正确问题  20170708  dyf
				  else if (response.length == 37) {	
					if (j == 8 && response[8] == 104 && response[9] == 129 && response[10] == 24 && response[36] == 22) {
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

			for (int i = 1; i < response.length - 2; i++) {
				sum += response[i];
			}
			crc = Integer.toHexString(sum % 0x100);
			return crc;
		}

		public static DTL64597Models GetDTL64597Models(int address) {
			DTL64597Models dtl645 = new DTL64597Models();
			dtl645.setAddress(36880);
			dtl645.setLength(18);
			switch (address) {
			case 1:
				dtl645.setAddress(36880);
				dtl645.setLength(19);
				break;// (当前)正向有功总电能(+A) 有功电能
			case 5:
				dtl645.setAddress(46609);
				dtl645.setLength(17);
				break;// A相电压
			case 7:
				dtl645.setAddress(46610);
				dtl645.setLength(17);
				break;// B相电压
			case 9:
				dtl645.setAddress(46611);
				dtl645.setLength(17);
				break;// C相电压
			case 11:
				dtl645.setAddress(46625);
				dtl645.setLength(17);
				break;// A相电流
			case 13:
				dtl645.setAddress(46626);
				dtl645.setLength(17);
				break;// B相电流
			case 15:
				dtl645.setAddress(46627);
				dtl645.setLength(17);
				break;// C相电流

			case 17:
				dtl645.setAddress(46640);
				dtl645.setLength(18);
				break;// 有功功率
			case 19:
				dtl645.setAddress(46641);
				dtl645.setLength(18);
				break;// A相有功功率
			case 21:
				dtl645.setAddress(46642);
				dtl645.setLength(18);
				break;// B相有功功率
			case 23:
				dtl645.setAddress(46643);
				dtl645.setLength(18);
				break;// C相有功功率

			case 25:
				dtl645.setAddress(46672);
				dtl645.setLength(17);
				break;// 功率因数
			case 27:
				dtl645.setAddress(46673);
				dtl645.setLength(17);
				break;// A相功率因数
			case 29:
				dtl645.setAddress(46674);
				dtl645.setLength(17);
				break;// A相功率因数
			case 31:
				dtl645.setAddress(46675);
				dtl645.setLength(17);
				break;// A相功率因数

			case 33:
				dtl645.setAddress(46656);
				dtl645.setLength(17);
				break;// 无功功率
			case 35:
				dtl645.setAddress(46657);
				dtl645.setLength(17);
				break;// A相无功功率
			case 37:
				dtl645.setAddress(46658);
				dtl645.setLength(17);
				break;// B相无功功率
			case 39:
				dtl645.setAddress(46659);
				dtl645.setLength(17);
				break;// C相无功功率

			case 41:
				dtl645.setAddress(37136);
				dtl645.setLength(19);
				break;// (当前)正向无功总电能
			case 43:
				dtl645.setAddress(37919);
				dtl645.setLength(19);
				break;// (上月)正向有功总电能(+A)
			case 45:
				dtl645.setAddress(38175);
				dtl645.setLength(19);
				break;// (上月)正向无功总电能
//46688   视在功率  （4个）   45424   电压不平衡             45440   电流不平衡          //dyf   20170920			
			case 47:
				dtl645.setAddress(46688);
				dtl645.setLength(18);
				break;   //瞬时视在功率
			case 49:
				dtl645.setAddress(46689);
				dtl645.setLength(18);
				break;    //瞬时A相视在功率
			case 51:
				dtl645.setAddress(46690);
				dtl645.setLength(18);
				break;
			case 53:       //瞬时B相实在功率
				dtl645.setAddress(46691);
				dtl645.setLength(18);
				break;
			case 55:         //瞬时C相视在功率
				dtl645.setAddress(46694);
				dtl645.setLength(17);
				break;
			case 57:         //电压不平衡率
				dtl645.setAddress(46695);
				dtl645.setLength(17);
				break;       //电流不平衡率
			case 59:
				dtl645.setAddress(46736);
				dtl645.setLength(18);
				break;      //频率
			case 61:
				dtl645.setAddress(36896);
				dtl645.setLength(19);
				break;// (当前)反向有功总电能
			case 63:
				dtl645.setAddress(37152);
				dtl645.setLength(19);
				break;// (当前)反向无功总电能


			}

			// 这里是特殊需求，标准的645协议里面没有谐波的，针对太原项目用
			if (address == 101 || address == 103 || address == 105 || address == 107 || address == 109 || address == 111
					|| address == 113 || address == 115 || address == 117 || address == 119 || address == 121) {
				
				dtl645.setAddress(63488);
				dtl645.setLength(37);// 当前电压A谐波含量
				//这里修改将原来的接收长度变为37  20170708  dyf
			} else if (address == 131 || address == 133 || address == 135 || address == 137 || address == 139
					|| address == 141 || address == 143 || address == 145 || address == 147 || address == 149
					|| address == 151) {

				dtl645.setAddress(63744);
				dtl645.setLength(37);// 当前电压B谐波含量

			}

			else if (address == 161 || address == 163 || address == 165 || address == 167 || address == 169
					|| address == 171 || address == 173 || address == 175 || address == 177 || address == 179
					|| address == 181) {
				dtl645.setAddress(64000);
				dtl645.setLength(37);// 当前电压C谐波含量

			}

			else if (address == 191 || address == 193 || address == 195 || address == 197 || address == 199
					|| address == 201 || address == 203 || address == 205 || address == 207 || address == 209
					|| address == 211) {
				dtl645.setAddress(64256);
				dtl645.setLength(37);// 当前电流A谐波含量

			}

			else if (address == 221 || address == 223 || address == 225 || address == 227 || address == 229
					|| address == 231 || address == 233 || address == 235 || address == 237 || address == 239
					|| address == 241) {
				dtl645.setAddress(64512);
				dtl645.setLength(37);// 当前电流B谐波含量

			}

			else if (address == 251 || address == 253 || address == 255 || address == 257 || address == 259
					|| address == 261 || address == 263 || address == 265 || address == 267 || address == 269
					|| address == 271) {
				dtl645.setAddress(64768);
				dtl645.setLength(37);// 当前电流C谐波含量
			}
			
			return dtl645;
		}
}
