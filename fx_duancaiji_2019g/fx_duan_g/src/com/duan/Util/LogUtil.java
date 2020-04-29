package com.duan.Util;

import org.apache.log4j.Logger;

import com.duan.domain.VCollectfield;

 
public class LogUtil {
	
	static final Logger log = Logger.getLogger(LogUtil.class);
	
    public static  void  printCollectLog(VCollectfield vcollectfield, String message) 
    {
    	log.info(vcollectfield.getMeterName()+" "+vcollectfield.getProtocolName()+" "+vcollectfield.getFieldName()+" "+message);
    }
    
    public static void  print(String message) 
    {
    	log.info(message);
    }
	
    public static void error(String message, Exception ex)
    {
    	log.error(message,ex);//错误异常完整写入日志
    }
    public static void error(String message)
    {
    	log.error(message);//错误异常完整写入日志
    }
 
}
