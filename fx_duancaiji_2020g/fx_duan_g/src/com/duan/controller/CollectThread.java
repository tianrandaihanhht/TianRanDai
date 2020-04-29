package com.duan.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.duan.domain.OpcConModel;
import com.duan.service.OpcConService;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.duan.Util.DateTimeHelper;
import com.duan.Util.LogUtil;
import com.duan.Util.SerialPortManager;
import com.duan.Util.StringUtil;
import com.duan.Util.XmlUtil;
import com.duan.Util.mudbus.ModbusRTUParser;

import com.duan.domain.DCom;
import com.duan.domain.VCollectfield;
import com.duan.service.DComService;
import com.duan.service.DDictionaryService;
import com.duan.service.VCollectfieldService;

import gnu.io.NRSerialPort;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.json.Json;
import javax.json.JsonObject;

public class CollectThread implements Runnable {

	@Autowired
	private VCollectfieldService vCollectfieldService;
	@Autowired
	private  OpcConService opcConService;

	@Autowired
	private static DComService dComService;

	private String xmlPath;

	@Autowired
	private DDictionaryService dDictionaryService;

	private static Map<String, SerialPort> serialPortMap = new HashMap<String, SerialPort>();

	public OpcConService getOpcConService() {
		return opcConService;
	}

	public void setOpcConService(OpcConService opcConService) {
		this.opcConService = opcConService;
	}

	public DDictionaryService getdDictionaryService() {
		return dDictionaryService;
	}

	public void setdDictionaryService(DDictionaryService dDictionaryService) {
		this.dDictionaryService = dDictionaryService;
	}

	public VCollectfieldService getvCollectfieldService() {
		return vCollectfieldService;
	}

	public void setvCollectfieldService(VCollectfieldService vCollectfieldService) {
		this.vCollectfieldService = vCollectfieldService;
	}

	public DComService getdComService() {
		return dComService;
	}

	public void setdComService(DComService dComService) {
		this.dComService = dComService;
	}

	public Map<String, SerialPort> getSerialPortMap() {
		return serialPortMap;
	}

	public void setSerialPortMap(Map<String, SerialPort> serialPortList) {
		this.serialPortMap = serialPortMap;
	}

	@Override
	public void run() {
		try {
			// 先打开所有的串口

			//openComs();
			startProtocolCollect(null, "2222222", 900);
			 //runCollect();
		} catch (Exception ex) {
			LogUtil.print("采集出错" + ex.getMessage());
			LogUtil.error(ex.getMessage(), ex);// 错误异常完整写入日志
		}
	}

	public void runCollect() {

		// List<Integer> distinctCycleList = vCollectfieldService.selectDistinctcycle();
		String zhouqi = dDictionaryService.getByName("采集周期").getValue();
		List<Integer> distinctCycleList = new ArrayList<Integer>();
		distinctCycleList.add(Integer.parseInt(zhouqi));
		// distinctCycleList.add(Integer.parseInt( zhouqi ));

		// Iterator<Integer> it1 = distinctCycleList.iterator();
		Iterator<Integer> it1 = distinctCycleList.iterator();
		while (it1.hasNext()) {
			final int intcycle = (int) it1.next();

			Date dat = new Date();

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				int currentcycle = intcycle;

				@Override
				public void run() {

					Date da = new Date();
					String strdate = DateTimeHelper.getStrTime(String.valueOf(da.getTime()));
					String fileName = strdate.replace(" ", "") + currentcycle + ".xml";

					LogUtil.print(strdate + "开始采集");
					// 采集队列
					List<VCollectfield> collectfieldlist = getCollectMeterField(currentcycle);

					startProtocolCollect(collectfieldlist, strdate, currentcycle);

					LogUtil.print(da + "结束采集");

				}
			}, dat, intcycle * 1000);
		}
	}

	/*
	 * 开始正式的 判断协议、 拼接协议、打开串口、发送协议、接收协议、解析协议、转换成xml
	 */
	public void startProtocolCollect(List<VCollectfield> collectfieldlist, String strdate, int currentcycle) {
		String xmlPath = dDictionaryService.getById("6a44136e-f999-4321-80f1-b23618d994f8").getValue();

		String fileName = strdate.replace(" ", "").replace(":", "") + currentcycle + ".xml";
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("coms");
//
//		Iterator it1 = collectfieldlist.iterator();
//		while (it1.hasNext()) {
//
//			VCollectfield fieldCollect = (VCollectfield) it1.next();
//			ProtocolBuilder.buildMessage(fieldCollect);
//			try {
//				byte[] sendMessage = null;
//
//				String comName = fieldCollect.getComName();
//
//				SerialPort serialport = serialPortMap.get(comName);
//
//				if (serialport != null) {
//					try {
//						sendMessage = ProtocolBuilder.buildMessage(fieldCollect);
//					} catch (Exception ex) {
//						LogUtil.printCollectLog(fieldCollect, "创建发送帧错误" + ex.getMessage());
//						continue;
//					}
//					String logMessage = StringUtil.Byte2HexString(sendMessage);
//					LogUtil.printCollectLog(fieldCollect, "即将发送消息 " + logMessage);
//					// 向串口发送消息
//					byte[] returnMessage;
//
//					returnMessage = sendMessage(serialport, sendMessage);
//					if (returnMessage != null) {
//						LogUtil.print("开始转换接受数据");
//						String sreturnMessage = StringUtil.Byte2HexString(returnMessage);
//						LogUtil.print("转换后的数据" + sreturnMessage);
//						// 解析返回的数据
//						String resoledMessage = ProtocolParser.parseMessage(fieldCollect, returnMessage);
//						LogUtil.print("解析返回消息 " + resoledMessage);
//						// 生成xml
//
//						if (resoledMessage != null) {
//							//增加opc数据
//
//							XmlUtil.generateXML(fieldCollect, resoledMessage, root);
//
//						}
//
//					} else {
//						LogUtil.printCollectLog(fieldCollect, "解析返回消息 " + "null");
//					}
//				} else {
//					LogUtil.printCollectLog(fieldCollect, "串口为空");
//				}
//			} catch (Exception ex) {
//				LogUtil.printCollectLog(fieldCollect, ex.getMessage());
//			}
//		}
		opcData(root);
		Writer out;
		try {
			out = new PrintWriter(xmlPath + fileName, "utf-8");
			// 格式化
			OutputFormat format = OutputFormat.createCompactFormat(); // 创建美化格式
			format.setEncoding("utf-8"); // 指定XML编码
			format.setIndent(true); // 设置是否缩进
			format.setNewlines(true); // 设置是否换行
			XMLWriter writer = new XMLWriter(out, format);
			// 把document对象写到out流中。
			try {
				writer.write(doc);
				out.close();
				writer.close();
			} catch (IOException e) {
				LogUtil.print("xml文件保存出错" + e.getMessage());
				LogUtil.error(e.getMessage(), e);// 错误异常完整写入日志
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			LogUtil.print("xml文件保存出错" + e.getMessage());
			LogUtil.error(e.getMessage(), e);// 错误异常完整写入日志
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			LogUtil.print("xml文件保存出错" + e.getMessage());
			LogUtil.error(e.getMessage(), e);// 错误异常完整写入日志
			e.printStackTrace();
			e.printStackTrace();
		}

	}

	private void  opcData(Element root){

		Properties prop = null;
		try {
			prop = PropertiesLoaderUtils.loadAllProperties("db.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 根据关键字查询相应的值
		String opcUrl = prop.getProperty("opc.host");


		List<OpcConModel> opcList = opcConService.findAllOpc();

		//List<OpcConModel> opcList = opcConService.selectByItemName("itemName");
		for(OpcConModel opc:opcList){


			String opcStr=post(opcUrl, JSON.toJSONString(opc));
			//Item: 通道 1.设备 1.1, Value: [[true]], Timestamp: 星期二 四月 28 10:04:44 CST 2020, Quality: 192
			VCollectfield fieldCollect=new VCollectfield();
			String[] opcstring=opcStr.split(",");
			String[]	opcshebei=opcstring[0].split(":");
			String[]	opcvalue=opcstring[1].split(":");
			fieldCollect.setMeterName(opcshebei[0]);

			fieldCollect.setMeterName(opcshebei[1]);
			String resoledMessage=opcvalue[1];
			fieldCollect.setComName("opcserverName");

			XmlUtil.generateXMLopc(fieldCollect, resoledMessage, root);
		}
	}

	/**
	 * 发送消息
	 * 
	 * @throws InterruptedException
	 */
	private byte[] sendMessage(SerialPort serialport, byte[] sendMessage) {

		byte[] rbyt = null;
		LogUtil.print("开始发送消息" + serialport.getName());
		SerialPortManager.sendToPort(serialport, sendMessage);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rbyt = SerialPortManager.readFromPort3(serialport);
		LogUtil.print("发送消息结束");
		return rbyt;

	}

	// System.arraycopy()方法
	public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
		byte[] bt3 = new byte[bt1.length + bt2.length];
		System.arraycopy(bt1, 0, bt3, 0, bt1.length);
		System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
		return bt3;
	}

	/*
	 * 重新打开所有的COM口
	 */
	public static void openComs() {
		serialPortMap.clear();
		ArrayList<String> ports = SerialPortManager.findPort(); // 获取本机子所有串口
		// 存放串口的列表
		Iterator it1 = ports.iterator();

		while (it1.hasNext()) {
			String portName = (String) it1.next(); // 端口号
			DCom com = dComService.getByName(portName);
			LogUtil.print("查询数据库中的串口信息" + portName);
			if (com != null) {
				LogUtil.print("开始打开串口" + portName);
				SerialPort sp = null;
				SerialPortManager.closePort(sp);
				try {
					sp = SerialPortManager.openPort(portName, com.getBaudrate(), com.getParity());
					serialPortMap.put(portName, sp);
				} catch (NoSuchPortException e) {
					LogUtil.print("打开串口" + portName + "失败");
					e.printStackTrace();
				}
				LogUtil.print("打开串口个数" + serialPortMap.size());
			}
		}
	}

	/*
	 * 获取串口下面的设备信息 参数：portName 串口名称 interval 采集间隔
	 */
	public List<VCollectfield> getCollectMeterField(int interval) {
		List<VCollectfield> collectfieldlist = vCollectfieldService.selectBycycle(interval);
		return collectfieldlist;
	}

	public static String post(String strURL, String params) {
		try {
			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			//connection.setRequestProperty("token", "ahCRYHeW6E2WxfdbKljWA_bPAlR6DY2Vw=");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式

			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(params);
			out.flush();
			out.close();
			// 读取响应
			String result ="";
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				BufferedReader bReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
				String strOutPut = "";
				StringBuilder sBuilder = new StringBuilder();

				while ((strOutPut = bReader.readLine()) != null) {
					sBuilder.append(strOutPut);
				}
				result=sBuilder.toString();
			}

			System.out.println("333"+result);
			return result;

		} catch (IOException e) {

			e.printStackTrace();
		}
		return "error"; // 自定义错误信息
	}

}
