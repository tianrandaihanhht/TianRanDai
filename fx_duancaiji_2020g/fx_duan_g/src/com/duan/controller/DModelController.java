package com.duan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.domain.DField;
import com.duan.domain.DModel;
import com.duan.domain.DProtocol;
import com.duan.service.DModelService;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.vo.Result;

@Controller
@RequestMapping("model")
public class DModelController {
	@Autowired
	private DModelService dModelService;

	@RequirePermission("model:listUI")
	@RequestMapping("listUI")
	public String listUI() {

		return "duan/model/listUI";
	}

	@RequirePermission("model:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit, String search) {
		PageInfo<DModel> pageInfo = this.dModelService.getListByPage(offset / limit + 1, limit, search);
		return Result.succeed(pageInfo);
	}

	@RequirePermission("model:setField")
	@RequestMapping("setField")
	public String setField(String modelid, HttpServletRequest request) {
		request.setAttribute("modelid", modelid);
		return "duan/model/setField";
	}

	@RequirePermission("model:listUI")
	@RequestMapping("findAlllist")
	@ResponseBody
	public Result findAlllist() {
		List<DModel> list = this.dModelService.getList();
		return Result.succeed(list);
	}
	// ===================================保存/修改/删除方法=======================================

	@RequestMapping("saveUI")
	public String saveUI(String id, HttpServletRequest request) {
		if (id != null && !id.equals("null")) {
			DModel DModel = this.dModelService.getById(id);
			if (DModel != null) {
				request.setAttribute("model", DModel);
			}
		}
		return "duan/model/saveUI";
	}

	@RequirePermission("model:add;model:update")
	@RequestMapping("save")
	public String add(DModel DModel) {
		if (DModel.getId() != null && !DModel.getId().equals("") && !DModel.getId().equals("null")) {
			this.dModelService.update(DModel);
		} else {
			DModel.setId(UUID.randomUUID().toString());
			this.dModelService.save(DModel);
		}
		return "redirect:listUI";
	}

	@RequirePermission("model:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {

		try {

			String[] idsStr = ids.split(",");
			if (idsStr.length == 1) {
				this.dModelService.deleteById(idsStr[0]);
			} else {
				this.dModelService.deleteBatchByIds(idsStr);
			}
			return Result.succeed();
		} catch (Exception ex) {
			return Result.fail(500, "该型号正在被使用！不能删除");
		}
	}

	@RequirePermission("model:setProtocol")
	@RequestMapping("setProtocol")
	@ResponseBody
	public Result setProtocol(String modelId, String protocolIds) {
		try {
			this.dModelService.saveModelProtocol(modelId, protocolIds);
			return Result.succeed();
		} catch (Exception ex) {
			return Result.fail(500, ex.getMessage());
		}
	}

	@RequirePermission("model:setField")
	@RequestMapping("saveField")
	@ResponseBody

	public Result saveField(String modelId, String fieldIds) {

		try {
			this.dModelService.saveModelFields(modelId, fieldIds);
			return Result.succeed();
		} catch (Exception ex) {
			return Result.fail(500, ex.getMessage());
		}

	}

}
