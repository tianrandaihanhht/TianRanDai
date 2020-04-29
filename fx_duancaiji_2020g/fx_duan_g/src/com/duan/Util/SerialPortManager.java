package com.duan.Util;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import com.duan.controller.ProtocolParser;

public class SerialPortManager implements java.io.Serializable {

	public static ArrayList<String> portNameList = null;
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查找所有可用端口
	 *
	 * @return 可用端口名称列表
	 */
	@SuppressWarnings("unchecked")
	public static final ArrayList<String> findPort() {

		if (portNameList != null)
			return portNameList;
		// 获得当前所有可用串口
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		portNameList = new ArrayList<String>();
		// 将可用串口名添加到List并返回该List
		while (portList.hasMoreElements()) {
			String portName = portList.nextElement().getName();

			// String portName = String.valueOf(portList.nextElement().getPortType());
			portNameList.add(portName);
		}
		return portNameList;
	}

	public static final ArrayList<String> findPort1() {
		ArrayList<String> portNameList = new ArrayList<String>();
		portNameList.add("/dev/ttyS0");
		portNameList.add("/dev/ttyS1");
		portNameList.add("/dev/ttyS2");
		portNameList.add("/dev/ttyS3");
		portNameList.add("/dev/ttyS4");
		portNameList.add("/dev/ttyS5");
		portNameList.add("/dev/ttyS6");
		portNameList.add("/dev/ttyS7");
		portNameList.add("/dev/ttyS8");
		portNameList.add("/dev/ttyS9");
		portNameList.add("/dev/ttyS10");
		portNameList.add("/dev/ttyS11");
		portNameList.add("/dev/ttyS12");
		portNameList.add("/dev/ttyS13");
		portNameList.add("/dev/ttyS14");

		return portNameList;
	}

	/**
	 * 打开串口
	 *
	 * @param portName 端口名称
	 * @param baudrate 波特率
	 * @param praity   校验方式
	 * @return 串口对象
	 * @throws NoSuchPortException
	 */
	public static final SerialPort openPort(String portName, int baudrate, String praity) throws NoSuchPortException {
		try {
			// 通过端口名识别端口
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
			// 打开端口，设置端口名与timeout（打开操作的超时时间）
			// CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
			LogUtil.print("portIdentifier.open()开始  " + portName);
			CommPort commPort = portIdentifier.open(portName, 2000);
			LogUtil.print("portIdentifier.open()结束  " + portName);
			// 判断是不是串口
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;

				try {
					// 设置串口的波特率等参数
					int pp = 0;// None
					if ("EVEN".equals(praity)) {
						LogUtil.print("校验位EVEN ");
						pp = SerialPort.PARITY_EVEN; // 偶校验
					} else if ("ODD".equals(praity)) {
						LogUtil.print("校验位ODD ");
						pp = SerialPort.PARITY_ODD;// 奇校验
					} else {

						pp = SerialPort.PARITY_NONE;// 无校验
						LogUtil.print("校验位NONE");
					}
					serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, pp);
				} catch (UnsupportedCommOperationException e) {
					LogUtil.print(e.getMessage());
					LogUtil.print("设置串口参数出错" + portName);
				}
				return serialPort;
			} else {
				LogUtil.print("不是串口");
			}
		} catch (PortInUseException e) {
			LogUtil.print(e.getMessage());
		}
		return null;
	}

	public static final SerialPort openPort1(String portName, int baudrate, String praity) throws NoSuchPortException {
		try {
			// 通过端口名识别端口
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
			// 打开端口，设置端口名与timeout（打开操作的超时时间）
			// CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
			LogUtil.print("portIdentifier.open()开始  " + portName);
			CommPort commPort = portIdentifier.open(portName, 2000);
			LogUtil.print("portIdentifier.open()结束  " + portName);
			// 判断是不是串口
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;

				try {
					// 设置串口的波特率等参数
					int pp = 0;// None
					if ("EVEN".equals(praity)) {
						LogUtil.print("校验位EVEN ");
						pp = SerialPort.PARITY_EVEN; // 偶校验
					} else if ("ODD".equals(praity)) {
						LogUtil.print("校验位ODD ");
						pp = SerialPort.PARITY_ODD;// 奇校验
					} else {

						pp = SerialPort.PARITY_NONE;// 无校验
						LogUtil.print("校验位NONE");
					}
					serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, pp);
				} catch (UnsupportedCommOperationException e) {
					LogUtil.print(e.getMessage());
					LogUtil.print("设置串口参数出错" + portName);
				}
				return serialPort;
			} else {
				LogUtil.print("不是串口");
			}
		} catch (PortInUseException e) {
			LogUtil.print(e.getMessage());
		}
		return null;
	}

	/**
	 * 关闭串口
	 *
	 * @param serialPort
	 */
	public static void closePort(SerialPort serialPort) {
		if (serialPort != null) {
			serialPort.close();
			serialPort = null;
		}
	}

	/**
	 * 向串口发送数据
	 *
	 * @param serialPort 串口对象
	 * @param order      待发送数据
	 */
	public static void sendToPort(SerialPort serialPort, byte[] order) {

		OutputStream out = null;
		try {

			out = serialPort.getOutputStream();
			out.write(order);
			out.flush();

		} catch (IOException e) {
			LogUtil.print("发送消息sendToPort" + e.getMessage());
			LogUtil.print(e.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
					LogUtil.print("OUT关里");
				}
			} catch (IOException e) {
				LogUtil.print("发送消息sendToPort" + e.getMessage());
				LogUtil.print(e.getMessage());
			}
		}

	}

	/**
	 * 从串口读取数据
	 *
	 * @param serialPort 当前已建立连接的SerialPort对象
	 * @return 读取到的数据
	 */
	public static byte[] readFromPort3(SerialPort serialPort) {
		DataInputStream in = null;
		byte[] bytes = new byte[100];
		byte[] resbytes = null;
		try {

			in = new DataInputStream(serialPort.getInputStream());
			// in =serialPort.getInputStream();
			// 获取buffer里的数据长度
		  
			int bufflenth = in.available();
			LogUtil.print("bufflenth" + bufflenth);
			int recut = 0;
			while (bufflenth != 0) {
				recut = in.read(bytes);
				bufflenth = in.available();
			}
			LogUtil.print("读入数据" + recut);
			if (recut != 0) {
				resbytes = new byte[recut];
				System.arraycopy(bytes, 0, resbytes, 0, recut);
			}

		} catch (IOException e) {
			LogUtil.print("发送消息readFromPort" + e.getMessage());
			LogUtil.print(e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				LogUtil.print("发送消息readFromPort" + e.getMessage());
				LogUtil.print(e.getMessage());
			}
		}
		return resbytes;
	}

	public static byte[] readFromPort(SerialPort serialPort) {
		DataInputStream in = null;
		byte[] bytes = null;

		try {

			in = new DataInputStream(serialPort.getInputStream());
			// in =serialPort.getInputStream();
			// 获取buffer里的数据长度
			int bufflenth = in.available();
			LogUtil.print("bufflenth" + bufflenth);
			try {
				bytes = readStream(in);
			} catch (Exception e) {
				LogUtil.print("读取串口信息出错" + e.getMessage());
			}

		} catch (IOException e) {
			LogUtil.print("发送消息readFromPort" + e.getMessage());
			LogUtil.print(e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				LogUtil.print("发送消息readFromPort" + e.getMessage());
				LogUtil.print(e.getMessage());
			}
		}
		return bytes;
	}

	public static byte[] readFromPort4(final SerialPort serialPort) {

		try {

			serialPort.addEventListener(new SerialPortEventListener() {
			    InputStream in =  serialPort.getInputStream();;
				byte[] bytes = new byte[100];

				@Override
				public void serialEvent(SerialPortEvent arg0) {
					try {
						int bufferPos = 0;
						int bufflenth = in.available();
						LogUtil.print("bufflenth" + bufflenth);
						while (in.available() > 0)
							bytes[bufferPos++] = (byte) in.read();
						LogUtil.print("	int bufferPos = 0;" + bufferPos);
						
						
						if (bytes != null) {
							LogUtil.print("开始转换接受数据");
							String sreturnMessage = StringUtil.Byte2HexString(bytes);
							LogUtil.print("转换后的数据"+sreturnMessage);			 
							// 解析返回的数据
							//String resoledMessage = ProtocolParser.parseMessage(fieldCollect, bytes);
							//LogUtil.print("解析返回消息 " + resoledMessage);
							// 生成xml
						 
							
						} else {
							LogUtil.print("解析返回消息 " + "null");
						}
						
					} catch (IOException e) {
						// we can't throw this exception, so the best we can do is log it and carry on.
						LogUtil.print("Error reading serial stream. Try downloading again.");
					} finally {
						try {
							if (in != null) {
								in.close();
								in = null;
							}
						} catch (IOException e) {
							LogUtil.print("发送消息readFromPort" + e.getMessage());
						
						}

					}
				}

			});

		} catch (IOException e) {
			LogUtil.print("发送消息readFromPort" + e.getMessage());
			LogUtil.print(e.getMessage());
		} catch (TooManyListenersException e) {
			LogUtil.print("发送消息readFromPort TooManyListenersException" + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	public static byte[] readFormat(byte[] inputbyte) {
		byte[] res = null;
		for (int i = (inputbyte.length - 1); i >= 0; i--) {
			if (inputbyte[i] != 0) {
				res = new byte[i + 1];
			}
		}
		System.arraycopy(inputbyte, 0, res, 0, res.length);

		return res;
	}

	/**
	 * 从串口读取数据
	 *
	 * @param serialPort 当前已建立连接的SerialPort对象
	 * @return 读取到的数据
	 */
	public static byte[] readFromPort2(SerialPort serialPort) {
		DataInputStream in = null;
		byte[] bytes = null;
		try {

			in = new DataInputStream(serialPort.getInputStream());
			// in =serialPort.getInputStream();
			// 获取buffer里的数据长度
			int bufflenth = in.available();
			LogUtil.print("bufflenth" + bufflenth);
			while (bufflenth != 0) {

				bytes = new byte[7];
				in.readFully(bytes, 0, 7);
				bufflenth = in.available();

			}

		} catch (IOException e) {
			LogUtil.print("发送消息readFromPort" + e.getMessage());
			LogUtil.print(e.getMessage());
		} finally {
			try {
				if (in != null) {

					in.close();
					in = null;
				}
			} catch (IOException e) {
				LogUtil.print("发送消息readFromPort" + e.getMessage());
				LogUtil.print(e.getMessage());
			}
		}
		return bytes;
	}

	/**
	 * 从串口读取数据
	 *
	 * @param serialPort 当前已建立连接的SerialPort对象
	 * @return 读取到的数据
	 */
	public static byte[] readFromPort1(SerialPort serialPort) {

		DataInputStream in = null;
		byte[] bytes = {};
		try {
			in = new DataInputStream(serialPort.getInputStream());
			// 缓冲区大小为一个字节
			byte[] readBuffer = new byte[1];
			int bytesNum = in.read(readBuffer);
			while (bytesNum > 0) {
				bytes = concat(bytes, readBuffer);
				bytesNum = in.read(readBuffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}

	/**
	 * 合并数组
	 * 
	 * @param firstArray  第一个数组
	 * @param secondArray 第二个数组
	 * @return 合并后的数组
	 */
	public static byte[] concat(byte[] firstArray, byte[] secondArray) {
		if (firstArray == null || secondArray == null) {
			return null;
		}
		byte[] bytes = new byte[firstArray.length + secondArray.length];
		System.arraycopy(firstArray, 0, bytes, 0, firstArray.length);
		System.arraycopy(secondArray, 0, bytes, firstArray.length, secondArray.length);
		return bytes;
	}

	/**
	 * 添加监听器
	 *
	 * @param port     串口对象
	 * @param listener 串口监听器
	 */
	public static void addListener(SerialPort port, SerialPortEventListener listener) {
		try {
			// 给串口添加监听器
			port.addEventListener(listener);
			// 设置当有数据到达时唤醒监听接收线程
			port.notifyOnDataAvailable(true);
			// 设置当通信中断时唤醒中断线程
			port.notifyOnBreakInterrupt(true);
		} catch (TooManyListenersException e) {
			LogUtil.print(e.getMessage());
		}
	}
}
