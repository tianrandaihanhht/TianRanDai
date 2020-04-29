package com.duan.Util.mudbus;

import com.duan.Util.IParser;

public class MudbusRtuUtil  {
	
	// mudbus协议帧
	/*
	 * 不带校验位的帧协议
	 * 
	 *  
	 */
	public byte[] getCommandMudbusRTU(byte[] data1) {
	   //byte[] data3 =  { (byte) 0x01, (byte) 0x03, (byte) 0x00, (byte) 0x01 ,(byte) 0x00, (byte) 0x08, (byte) 0x15, (byte) 0xCC};;
	   //byte[] data1={ (byte) 0x01, (byte) 0x03, (byte) 0x00, (byte) 0x01 ,(byte) 0x00, (byte) 0x08};
		 
		byte[] data2 =CRCUtil.getCRC3( data1);
		
		byte[]  data = new byte[data1.length + data2.length];
		System.arraycopy(data1, 0, data, 0, data1.length);
		System.arraycopy(data2, 0, data, data1.length, data2.length);
		return data; 
	}
 
	
	/*
	 * 创建发送协议帧
	 * */
	public static  byte[] BuildMessage(int meteraddress, int address, int length, int code) 
	{    
		      byte[] message = new byte[8];
		        
				message[0] =(byte)  meteraddress;
				message[1] = (byte) code;
				message[2] = (byte) (address >> 8);
				message[3] = (byte) address;
				if (address >= 1023) {
					if ((byte) address < 0) {
						message[3] = (byte) (256 + (byte) address);
					} else {
						message[3] = (byte) address;
					}

				} else if (address > 256) {
					message[3] = (byte ) (address - 256);
				} else {
					message[3] = (byte) (address);
				}

				message[4] = (byte) (length >> 8);
				message[5] = (byte) length;
				message[0] = (byte) (message[0] % 256);    
			    message[1] = (byte) (message[1] % 256);
			    message[2] = (byte) (message[2] % 256);
			    message[3] = (byte) (message[3] % 256);
			    message[4] = (byte) (message[4] % 256);
			    message[5] = (byte) (message[5] % 256);
				byte[] crc = CRCUtil.getCRC3(message);

				message[6] = crc[0];
				message[7] = crc[1];

				return message;
	}

 
	
}
