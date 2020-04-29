package com.duan.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import com.duan.domain.DMeter;

import com.duan.domain.VModelField;
import com.duan.service.DMeterService;
import com.duan.service.impl.DCollectConfigServiceImpl;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.vo.Result;

@Controller
@RequestMapping("meter")
public class DMeterController {

	@Autowired
	private DMeterService dMeterService;
	@Autowired
	private DCollectConfigServiceImpl dCollectConfigServiceImpl;

	@RequirePermission("meter:listUI")
	@RequestMapping("listUI")
	public String listUI() {

		return "duan/meter/listUI";
	}

	@RequirePermission("meter:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit, String search) {

		PageInfo<DMeter> pageInfo = this.dMeterService.findPageWithResult(search, null, null, offset / limit + 1,
				limit);
		return Result.succeed(pageInfo);
	}

	// ===================================保存/修改/删除方法=======================================

	@RequestMapping("saveUI")
	public String saveUI(String id, HttpServletRequest request) {
		if (id != null) {
			DMeter dMeter = this.dMeterService.getById(id);
			if (dMeter != null) {
				request.setAttribute("meter", dMeter);
			}
		}
		return "duan/meter/saveUI";
	}

	@RequirePermission("meter:add;meter:update")
	@RequestMapping("save")
	public String add(DMeter dMeter) {
		if (dMeter.getId() != null && dMeter.getId() != "") {
			this.dMeterService.update(dMeter);
		} else {
			dMeter.setId(UUID.randomUUID().toString());
			this.dMeterService.save(dMeter);
		}
		return "redirect:listUI";
	}

	@RequirePermission("meter:add;meter:update")
	@RequestMapping("addfields")
	@ResponseBody
	public Result addField(DMeter dMeter) {
		try {
			List<VModelField> list = JSONArray.parseArray(dMeter.getFields(), VModelField.class);
			dMeter.setFieldlist(list);

			if (dMeter.getId() != null && !dMeter.getId().equals("") && !dMeter.getId().equals("null")) {
				this.dMeterService.updateAndCollectField(dMeter);

			} else {

				dMeter.setId(UUID.randomUUID().toString());
				this.dMeterService.save(dMeter);
				this.dMeterService.updateAndCollectField(dMeter);
			}
			//return "redirect:listUI";
			return Result.succeed();
		} catch (Exception ex) {
			return Result.succeed();
		}

	}

	@RequirePermission("meter:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {
		try {
			String[] idsStr = ids.split(",");
			if (idsStr.length == 1) {
				this.dMeterService.deleteById(idsStr[0]);
			} else {
				this.dMeterService.deleteBatchByIds(idsStr);
			}
			return Result.succeed();
		} catch (Exception ex) {
			return Result.fail(500, "仪表还有参数正在被采集！不能删除");
		}
	}

}
