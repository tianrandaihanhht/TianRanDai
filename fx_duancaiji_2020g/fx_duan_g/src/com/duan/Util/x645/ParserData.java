package com.duan.Util.x645;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

 
public class ParserData {
	/**
	 * 保留3位小数
	 * 
	 * @param value
	 * @return
	 */
	public static double convertValue(double value) {
		/*
		 * BigDecimal bg = new BigDecimal(value); double value1 = bg.setScale(2,
		 * BigDecimal.ROUND_HALF_UP).doubleValue(); return value1;
		 */

		return convertValue(value + "");
	}

	/**
	 * 保留3位小数
	 * 
	 * @param value
	 * @return
	 */
	public static double convertValue(String value) {
		BigDecimal bg = new BigDecimal(value);
		double value1 = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value1;
	}

	/**
	 * 把时间 对齐
	 * 
	 * @param day
	 * @param freezeTime
	 * @return
	 */
	public static String convertTime(String day, int freezeTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";

		Calendar cal = Calendar.getInstance();
		for (int i = 0; i <= 30; i++) {
			cal.setTime(date);
			cal.add(Calendar.MINUTE, -1);// 24小时制
			date = cal.getTime();
			// Log.d("测试", date.getMinutes() + "date.getMinutes()");
			if (date.getMinutes() % freezeTime == 0) {
				break;
			}
		}

		cal = null;
		// Log.d("测试", format.format(date));
		return format.format(date);

	}

	/**
	 * 转换数据,把byte数组转换为int数组
	 * 
	 * @param buffer
	 * @return
	 */
	public static int[] convertData(byte[] buffer) {
		int[] bytes = new int[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			if (buffer[i] < 0) {
				bytes[i] = 256 + buffer[i];

			} else {
				bytes[i] = buffer[i];
			}
		}
		return bytes;
	}

	

}
