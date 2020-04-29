package com.duan.Util.x645;

 
import com.duan.Util.IntUtil;
import com.duan.domain.VCollectfield;

public class Build645Util {

	public static byte[] build64597Message(String meterAddress, int startAddress) {
		//DTL64597Models dTL64597Models = DTL64597.GetDTL64597Models(startAddress);
		long l = Long.parseLong(meterAddress);
		int[] message = DTL64597.buildMessage1(l, startAddress);
		byte[] res = IntUtil.IntArrrayToByteArray(message);
		return res;
	}

	public static byte[] build64507Message(String meterAddress, int startAddress) { 
		long l = Long.parseLong(meterAddress);
		int[] message15 = DTL64507.buildMessage07( l , startAddress);
		byte[] res = IntUtil.IntArrrayToByteArray(message15);
		return res;
	}

}
