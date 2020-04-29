package com.duan.Util.x645;

import com.duan.Util.DataHandleTool;
import com.duan.Util.IParser;

public class DLT645_07_F2015Parser implements IParser {
	@Override
	public double Parse(int startAddress, int length, int[] buffer, float factor, String IsBigEndian,String aa) {

		StringBuffer tmp = new StringBuffer();
		//StringBuffer tmp1 = new StringBuffer();
		//StringBuffer tmp2 = new StringBuffer();
		if (startAddress >= 1 && startAddress < 101) {
			for (int i = buffer.length - 1; i >= 0; i--) {
				tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[i]), 2));
			}
		}
		 else if (startAddress >= 102)          //这里是特殊需求，标准的645协议里面没有谐波的，针对太原项目用
         {
			// MyLog.write485("开始地址1" + startAddress);
			// MyLog.write485("字符串1" + buffer);   
			 //新增645协议解析谐波的代码，645收回为22个字节，2个为一个值。20170708   dyf
             if ((startAddress - 102) % 30 == 0)          //A相电压谐波
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[1]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[0]), 2));
             }
             else if ((startAddress - 104) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[3]), 2));// 3次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[2]), 2));
             }
             else if ((startAddress - 106) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[5]), 2));// 5次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[4]), 2));
             }
             else if ((startAddress - 108) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[7]), 2));// 7次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[6]), 2));
             }
             else if ((startAddress - 110) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[9]), 2));// 9次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[8]), 2));
             }
             else if ((startAddress - 112) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[11]), 2));// 11次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[10]), 2));
             }
             else if ((startAddress - 114) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[13]), 2));// 13次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[12]), 2));
             }
             else if ((startAddress - 116) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[15]), 2));// 15次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[14]), 2));
             }
             else if ((startAddress - 118) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[17]), 2));// 17次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[16]), 2));
             }
             else if ((startAddress - 120) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[19]), 2));// 19次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[18]), 2));
             }
             else if ((startAddress - 122) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[21]), 2));// 21次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[20]), 2));
             }
            
            
         }
		 else if (startAddress >= 132)          //这里是特殊需求，标准的645协议里面没有谐波的，针对太原项目用
         {
			// MyLog.write485("开始地址2" + startAddress);
			// MyLog.write485("字符串2" + buffer);
             if ((startAddress - 134) % 30 == 0)             //B相电压谐波
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[1]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[0]), 2));//总谐波
             }
             else if ((startAddress - 136) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[3]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[2]), 2));// 3次谐波
             }
             else if ((startAddress - 136) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[5]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[4]), 2));// 5次谐波
             }
             else if ((startAddress - 138) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[7]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[6]), 2));// 7次谐波
             }
             else if ((startAddress - 140) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[9]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[8]), 2));// 9次谐波
             }
             else if ((startAddress - 142) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[11]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[10]), 2));// 11次谐波
             }
             else if ((startAddress - 144) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[13]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[12]), 2));// 13次谐波
             }
             else if ((startAddress - 146) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[15]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[14]), 2));// 15次谐波
             }
             else if ((startAddress - 148) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[17]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[16]), 2));// 17次谐波
             }
             else if ((startAddress - 150) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[19]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[18]), 2));// 19次谐波
             }
             else if ((startAddress - 152) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[21]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[20]), 2));// 21次谐波
             }
            
            
         }
		 else if (startAddress >= 162)          //这里是特殊需求，标准的645协议里面没有谐波的，针对太原项目用
         {
			// MyLog.write485("开始地址3" + startAddress);
			// MyLog.write485("字符串3" + buffer);
             if ((startAddress - 162) % 30 == 0)          //C相电压谐波
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[1]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[0]), 2));//总谐波
             }
             else if ((startAddress - 164) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[3]), 2));// 3次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[2]), 2));
             }
             else if ((startAddress - 166) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[5]), 2));// 5次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[4]), 2));
             }
             else if ((startAddress - 168) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[7]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[6]), 2));// 7次谐波
             }
             else if ((startAddress - 170) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[9]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[8]), 2));// 9次谐波
             }
             else if ((startAddress - 172) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[11]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[10]), 2));// 11次谐波
             }
             else if ((startAddress - 174) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[13]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[12]), 2));// 13次谐波
             }
             else if ((startAddress - 176) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[15]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[14]), 2));// 15次谐波
             }
             else if ((startAddress - 178) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[17]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[16]), 2));// 17次谐波
             }
             else if ((startAddress - 180) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[19]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[18]), 2));// 19次谐波
             }
             else if ((startAddress - 182) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[21]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[20]), 2));// 21次谐波
             }
            
            
         }
		 else if (startAddress >= 192)          //这里是特殊需求，标准的645协议里面没有谐波的，针对太原项目用
         {
			// MyLog.write485("开始地址4" + startAddress);
			// MyLog.write485("字符串4" + buffer);
             if ((startAddress - 192) % 30 == 0)            //A相电流谐波
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[1]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[0]), 2));//总谐波
             }
             else if ((startAddress - 194) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[3]), 2));// 3次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[2]), 2));
             }
             else if ((startAddress - 196) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[5]), 2));// 5次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[4]), 2));
             }
             else if ((startAddress - 198) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[7]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[6]), 2));// 7次谐波
             }
             else if ((startAddress - 200) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[9]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[8]), 2));// 9次谐波
             }
             else if ((startAddress - 202) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[11]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[10]), 2));// 11次谐波
             }
             else if ((startAddress - 204) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[13]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[12]), 2));// 13次谐波
             }
             else if ((startAddress - 206) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[15]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[14]), 2));// 15次谐波
             }
             else if ((startAddress - 208) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[17]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[16]), 2));// 17次谐波
             }
             else if ((startAddress - 210) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[19]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[18]), 2));// 19次谐波
             }
             else if ((startAddress - 212) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[21]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[20]), 2));// 21次谐波
             }
            
            
         }
		 else if (startAddress >= 222)          //这里是特殊需求，标准的645协议里面没有谐波的，针对太原项目用
         {
			// MyLog.write485("开始地址5" + startAddress);
			// MyLog.write485("字符串5" + buffer);
             if ((startAddress - 222) % 30 == 0)              //B相电流谐波
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[1]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[0]), 2));//总谐波
             }
             else if ((startAddress - 224) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[3]), 2));// 3次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[2]), 2));
             }
             else if ((startAddress - 226) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[5]), 2));// 5次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[4]), 2));
             }
             else if ((startAddress - 228) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[7]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[6]), 2));// 7次谐波
             }
             else if ((startAddress - 230) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[9]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[8]), 2));// 9次谐波
             }
             else if ((startAddress - 232) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[11]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[10]), 2));// 11次谐波
             }
             else if ((startAddress - 234) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[13]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[12]), 2));// 13次谐波
             }
             else if ((startAddress - 236) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[15]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[14]), 2));// 15次谐波
             }
             else if ((startAddress - 238) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[17]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[16]), 2));// 17次谐波
             }
             else if ((startAddress - 240) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[19]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[18]), 2));// 19次谐波
             }
             else if ((startAddress - 242) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[21]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[20]), 2));// 21次谐波
             }
            
            
         }
		 else if (startAddress >= 252)          //这里是特殊需求，标准的645协议里面没有谐波的，针对太原项目用
         {
			// MyLog.write485("开始地址6" + startAddress);
			//.write485("字符串6" + buffer);
             if ((startAddress - 252)%30 == 0)                  //C相电流谐波
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[1]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[0]), 2));//总谐波
             }
             else if ((startAddress - 254) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[3]), 2));// 3次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[2]), 2));
             }
             else if ((startAddress - 256) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[5]), 2));// 5次谐波
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[4]), 2));
             }
             else if ((startAddress - 258) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[7]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[6]), 2));// 7次谐波
             }
             else if ((startAddress - 260) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[9]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[8]), 2));// 9次谐波
             }
             else if ((startAddress - 262) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[11]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[10]), 2));// 11次谐波
             }
             else if ((startAddress - 264) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[13]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[12]), 2));// 13次谐波
             }
             else if ((startAddress - 266) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[15]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[14]), 2));// 15次谐波
             }
             else if ((startAddress - 268) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[17]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[16]), 2));// 17次谐波
             }
             else if ((startAddress - 270) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[19]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[18]), 2));// 19次谐波
             }
             else if ((startAddress - 272) % 30 == 0)
             {
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[21]), 2));
            	 tmp.append(DataHandleTool.addZeroForNum(Integer.toHexString(buffer[20]), 2));// 21次谐波
             }
 
         }

		double meterValue = Integer.parseInt(tmp.toString()) * factor;
		return meterValue;

	}

}
