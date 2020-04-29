package com.duan.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.domain.DMeter;
import com.duan.domain.DModelField;
import com.duan.service.DMeterService;
import com.duan.service.DModelFieldService;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.vo.Result;

@Controller
@RequestMapping("modelfield")
public class DModelFieldController {

	@Autowired
	private DModelFieldService dMeterFieldService;

	@RequirePermission("modelfield:listUI")
	@RequestMapping("listUI")
	public String listUI() {

		return "duan/modelfield/listUI";
	}

	@RequirePermission("modelfield:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit, String search) {
		
		PageInfo<DModelField> pageInfo = this.dMeterFieldService.findPageWithResult(search, null, offset / limit + 1, limit);
		return Result.succeed(pageInfo);
	}

	//===================================保存/修改/删除方法=======================================

	@RequestMapping("saveUI")
	public String saveUI(String id, HttpServletRequest request) {
		if (id != null) {
			DModelField dMeter = this.dMeterFieldService.getById(id);
			if (dMeter != null) {
				request.setAttribute("meter", dMeter);
			}
		}
		return "duan/modelfield/saveUI";
	}

	@RequirePermission("modelfield:add;modelfield:update")
	@RequestMapping("save")
	public String add(DModelField dModelField) {
		if (dModelField.getId() != null && !dModelField.getId().equals("")&&!dModelField.getId().equals("null")) {
			this.dMeterFieldService.update(dModelField);
		} else {
			dModelField.setId(UUID.randomUUID().toString());
			this.dMeterFieldService.save(dModelField);
		}
		return "redirect:listUI";
	}

	@RequirePermission("modelfield:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {
        try {
        	String[] idsStr = ids.split(",");
    		if (idsStr.length == 1) {
    			this.dMeterFieldService.deleteById(idsStr[0]);
    		} else {
    			this.dMeterFieldService.deleteBatchByIds(idsStr);
    		}
    		return Result.succeed();
        }catch(Exception ex) 
        {
        	return Result.fail(500, "该型号参数正在被使用！不能删除");
        }
		
	}
}
