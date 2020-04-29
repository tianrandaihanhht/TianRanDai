package com.duan.Util;

 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.duan.Util.x645.ParserData;
 
 

public class DataHandleTool {
	public static double getMax(ArrayList<LiveTimeData> list) {
		double maxvalue = 0;
		for (int i = 0; i < list.size(); i++) {
			double value = Double.parseDouble(list.get(i).getTotal_data());
			if (maxvalue <= value) {
				maxvalue = value;
			}
		}
		return maxvalue;
	}

	public static double getMin(ArrayList<LiveTimeData> list) {
		if (list.size() == 0) {
			return 0;
		}
		double minvalue = Double.parseDouble(list.get(0).getTotal_data());
		for (int i = 0; i < list.size(); i++) {
			double value = Double.parseDouble(list.get(i).getTotal_data());
			if (minvalue >= value) {
				minvalue = value;
			}
		}
		return minvalue;
	}

	public static double getavg(ArrayList<LiveTimeData> list) {

		double avgvalue = 0;
		for (int i = 0; i < list.size(); i++) {
			double value = Double.parseDouble(list.get(i).getTotal_data());
			avgvalue = avgvalue + value;
		}
		return ParserData.convertValue(avgvalue / list.size() + "");
	}

	/**
	 * 获取2个数据对比的百分比
	 * 
	 * @param currentData
	 * @param prevData
	 * @return
	 */
	public static String getProgressData(String currentData, String prevData) {
		float C = Float.parseFloat(currentData);
		float L = Float.parseFloat(prevData);

		try {
			float a = (C - L) * 100;
			float result = a / C;
			if (Float.isNaN(result) || Float.isInfinite(result))
				return String.valueOf(0) + "%";
			else
				return String.format("%.2f", result) + "%";
		} catch (ArithmeticException e) {
			float result = 0;
			return String.valueOf(result) + "%";
		}

	}

	public static SimpleDateFormat GetSimpleDateFormat(int baselinetype) {
		SimpleDateFormat format = null;
		switch (baselinetype) {
		case 1:
			// format = new SimpleDateFormat("HH:mm");
			format = new SimpleDateFormat("HH:mm");
			break;
		case 2:
			// format = new SimpleDateFormat("dd HH");
			format = new SimpleDateFormat("dd HH");
			break;
		case 3:
			// format = new SimpleDateFormat("MM-dd");
			format = new SimpleDateFormat("MM/dd");
			break;
		case 4:
			// format = new SimpleDateFormat("MM-dd");
			format = new SimpleDateFormat("MM/dd");
			break;
		case 5:
			// format = new SimpleDateFormat("yyyy-MM");
			format = new SimpleDateFormat("yyyy/MM");
			break;

		}
		return format;
	}

	/**
	 * 比较2个时间大小 开始时间小于等于结束时间 返回true
	 * 
	 * @param date1
	 *            开始时间
	 * @param date2
	 *            结束时间
	 * @return
	 */
	public static boolean compareDate(String date1, String date2) {
		boolean falg = false;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(date1);

			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				falg = false;
			} else {
				falg = true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return falg;
	}
	/*
	 * 数字不足位数左补0
	 *
	 * @param str
	 * 
	 * @param strLength
	 */
	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();

		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// 左补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}
}
