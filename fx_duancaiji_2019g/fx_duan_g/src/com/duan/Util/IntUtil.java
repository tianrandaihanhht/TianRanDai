package com.duan.Util;

public class IntUtil {

	/*
	 * 
	 * */  
	public static  byte[] IntArrrayToByteArray( int[] intarray)
	{
		int length=intarray.length;
		byte[] resArray=new byte[length];
		
		for(int i=0; i<length;i++) 
		{
			resArray[i]=(byte)intarray[i];
		}
		return resArray;
		
	}
	
	public static  int[] ByteArrayToIntArrray( byte[]  bytearray)
	{
		int length=bytearray.length;
		int[] resArray=new int[length];
		
		for(int i=0; i<length;i++) 
		{
			resArray[i]=(int)bytearray[i];
		}
		return resArray;
		
	}
}
