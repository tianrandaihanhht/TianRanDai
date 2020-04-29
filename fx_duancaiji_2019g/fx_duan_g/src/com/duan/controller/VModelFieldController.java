package com.duan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

 
import com.duan.domain.VModelField;
import com.duan.service.DMeterService;
import com.duan.service.VModelFieldService;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.vo.Result;

@Controller
@RequestMapping("modelfield")
public class VModelFieldController {
	
	@Autowired
	private VModelFieldService vModelFieldService;
	
	@Autowired
	private DMeterService dMeterService;
	
	@RequirePermission("meter:add;meter:update")
	@RequestMapping("getFieldListWithChecked/{meterId}")
	@ResponseBody
	public Result getFieldListWithChecked(int offset, int limit,String search,@PathVariable("meterId")String  meterId,String value1) 
	{					 
	 
		PageInfo<VModelField> protocolList = this.vModelFieldService.getFieldListWithCheckedAndPaged(offset / limit + 1, limit, meterId,value1);
		return Result.succeed(protocolList);				 
	}
}
