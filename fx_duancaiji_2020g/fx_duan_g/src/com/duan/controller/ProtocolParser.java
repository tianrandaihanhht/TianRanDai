package com.duan.controller;

import com.duan.Util.IntUtil;
import com.duan.Util.mudbus.ModbusFloatParser;
import com.duan.Util.mudbus.ModbusRTUParser;
import com.duan.Util.x645.DLT645_07Parser;
import com.duan.Util.x645.DLT645_97Parser;
import com.duan.domain.VCollectfield;

public class ProtocolParser {

	/*
	 * 解析返回的消息
	 */
	public static String parseMessage(VCollectfield fieldCollect, byte[] returnMessage) {
		String res = "";

		int startAddress = 0;
		String protocolName = fieldCollect.getProtocolName();

		if (protocolName.equals("645_97") || protocolName.equals("645_07")) {

			startAddress = Integer.parseInt(fieldCollect.getData1(), 16);
		} else {
			startAddress = Integer.parseInt(fieldCollect.getData1());
		}
		String data2 = fieldCollect.getData2();
		int endAddress = 0;
		int length = 0;
		if ((protocolName.equals("MODBUS_04")||protocolName.equals("MODBUS_03"))&&data2 != null && data2 != "" && data2.length() > 0) {
			endAddress = Integer.parseInt(data2);
			length = (endAddress - startAddress) + 1;
		}

		String parserid = fieldCollect.getParseId();
		float factor = fieldCollect.getFactor();
		Integer IsBigEndian = fieldCollect.getIsBigEndian();
		Integer InventedParameterType = fieldCollect.getInventedParameterType();
		int[] intarrayRetMessage = IntUtil.ByteArrayToIntArrray(returnMessage);

		try {
			if (parserid.equals("ABB2FF86-92BB-4D29-8643-1966D801C001")) {
				double a = new ModbusRTUParser().Parse(startAddress, length, intarrayRetMessage, factor,
						IsBigEndian.toString(), InventedParameterType.toString());
				res = String.valueOf(a);
			} else if (parserid.equals("91A7B2B5-D561-42CD-BA28-4BC5FAC8A43B")) {
				// Modbus float
				ModbusFloatParser mFloatParser = new ModbusFloatParser();
				double value = mFloatParser.Parse(startAddress, length, intarrayRetMessage, factor,
						IsBigEndian.toString(), InventedParameterType.toString());
			} else if (parserid.equals("4C055AC7-3785-43A2-9787-04092DF179BA")) {
				// 645_97
				DLT645_97Parser dlt645_97Parser = new DLT645_97Parser();
				double value = dlt645_97Parser.Parse(startAddress, length, intarrayRetMessage, factor,
						IsBigEndian.toString(), InventedParameterType.toString());
				res = String.valueOf(value);
			} else if (parserid.equals("45e5a695-2d70-449e-8209-c19f327208ec")) {

			} else if (parserid.equals("404967CA-79B0-46D5-8222-2A7F349B21A2")
					|| parserid.equals("69de3af8-6684-4a4f-9d3e-8c2aaa07ae03")
					|| parserid.equals("e157e378-fad5-4947-ae2e-bf8bd08b842f")) {
				DLT645_07Parser DLT645_07Parser = new DLT645_07Parser();
				double value = DLT645_07Parser.Parse(startAddress, length, intarrayRetMessage, factor,
						IsBigEndian.toString(), InventedParameterType.toString());
				res = String.valueOf(value);
			} else {
				return null;
			}
		} catch (Exception err) {

			return null;
		}

		return res;
	}
}
