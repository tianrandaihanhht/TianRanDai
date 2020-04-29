package com.duan.controller;
 
import java.util.List;
import java.util.Map;

import com.duan.service.OpcConService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.Util.LogUtil;
import com.duan.domain.VCollectfield;
 
import com.duan.service.DComService;
import com.duan.service.DDictionaryService;
import com.duan.service.VCollectfieldService;
import com.test.annotation.RequirePermission;

import com.test.vo.Result;

import gnu.io.SerialPort;

@Controller
@RequestMapping("collectstartup")
public class CollectStartupController {
   private static  boolean caijiflag=false;
	@Autowired
	private OpcConService opcConService;
	@Autowired
	private DComService dComService;
	
	@Autowired
	private DDictionaryService dDictionaryService;
	@Autowired
	private VCollectfieldService vCollectfieldService;

	private Map<Integer, List<VCollectfield>> cycleMap;

	@RequirePermission("collectstartup:operationUI")
	@RequestMapping("operationUI")
	public String operationUI() {
		return "duan/collectstartup/operationUI";
	}

	@RequirePermission("collectstartup:startup")
	@RequestMapping("startup")
	@ResponseBody
	public Result startup() {
	
		CollectThread c=new CollectThread();
		c.setvCollectfieldService(this.vCollectfieldService);
		c.setOpcConService(this.opcConService);
		c.setdComService(this.dComService);
		c.setdDictionaryService(this.dDictionaryService); 
		//c.run();
		if(caijiflag==false)
		{    
			 caijiflag=true;
			 Thread thread=new Thread(c);
			 thread.start();
	
			 return Result.succeed();
		}else {
			 return Result.fail(500, "采集程序正在进行，无法重复启动！");
		}
	
	
	}
}
