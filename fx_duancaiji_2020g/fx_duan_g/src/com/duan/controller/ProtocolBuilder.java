package com.duan.controller;


import com.duan.Util.LogUtil;
import com.duan.Util.mudbus.MudbusRtuUtil;
import com.duan.Util.x645.Build645Util;
import com.duan.domain.VCollectfield;

public class ProtocolBuilder {

	public static byte[] buildMessage(VCollectfield fieldCollect) {

		int meterAddress = Integer.parseInt(fieldCollect.getMeterAddress());
		
		int startAddress =0;
		String protocolName = fieldCollect.getProtocolName();
		if(protocolName.equals("645_97"))
		{  	 
		      startAddress =  Integer.parseInt(fieldCollect.getData1(), 16);
		      
		}else if(protocolName.equals("645_07")) {
			  startAddress =  Integer.parseInt(fieldCollect.getData1(), 16);
		}
		else {
		     startAddress = Integer.parseInt(fieldCollect.getData1());
		}
	    String 	data2= fieldCollect.getData2();
		int endAddress=0;
		int length =0;
		if((protocolName.equals("MODBUS_04")||protocolName.equals("MODBUS_03"))&&data2!=null&&data2!=""&&data2.length()>0) 
		{
		  endAddress = Integer.parseInt(data2);
		  length = (endAddress - startAddress) + 1;
		  LogUtil.print("发送消息"+endAddress+"-"+startAddress+"长度"+length );
		 
		}
		byte[] sendMessage = null;
		if (protocolName.equals("MODBUS_03")) {
			  LogUtil.print("发送消息长度"+length );
			sendMessage = MudbusRtuUtil.BuildMessage(meterAddress, startAddress, length, 3);
			
		} else if (protocolName.equals("MODBUS_04")) {
			sendMessage = MudbusRtuUtil.BuildMessage(meterAddress, startAddress, length, 4);
		} else if (protocolName.equals("645_07")) {
			sendMessage = Build645Util.build64507Message(String.valueOf(meterAddress), startAddress);
		} else if (protocolName.equals("645_97")) {
		 
			sendMessage = Build645Util.build64597Message(String.valueOf(meterAddress), startAddress);
		}
		return sendMessage;
	}
}
