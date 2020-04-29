package com.duan.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.domain.DField;

import com.duan.service.DFieldService;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.vo.Result;

@Controller
@RequestMapping("field")
public class DFieldController {

	@Autowired
	private DFieldService dFieldService;

	@RequirePermission("field:listUI")
	@RequestMapping("listUI")
	public String listUI() {
		return "duan/field/listUI";
	}

	@RequirePermission("field:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit, String search) {
		PageInfo<DField> pageInfo = this.dFieldService.getListByPage(offset / limit + 1, limit, search);
		return Result.succeed(pageInfo);
	}

	@RequirePermission("field:listUI")
	@RequestMapping("findAlllist")
	@ResponseBody
	public Result findAlllist() {
		List<DField> list = this.dFieldService.getList();
		return Result.succeed(list);
	}
	// ===================================保存/修改/删除方法=======================================

	@RequestMapping("saveUI")
	public String saveUI(String id, HttpServletRequest request) {
		if (id != null) {
			DField DField = this.dFieldService.getById(id);
			if (DField != null) {
				request.setAttribute("field", DField);
			}
		}
		return "duan/field/saveUI";
	}

	@RequirePermission("field:add;field:update")
	@RequestMapping("save")
	public String add(DField DField) {
		if (DField.getId() != null && !DField.getId().equals("") && !DField.getId().equals("null")) {
			this.dFieldService.update(DField);
		} else {
			DField.setId(UUID.randomUUID().toString());
			this.dFieldService.save(DField);
		}
		return "redirect:listUI";

	}

	@RequirePermission("field:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {
		try {

			String[] idsStr = ids.split(",");
			if (idsStr.length == 1) {
				this.dFieldService.deleteById(idsStr[0]);
			} else {
				this.dFieldService.deleteBatchByIds(idsStr);
			}
			return Result.succeed();
		} catch (Exception ex) {
			return Result.fail(500, "参数正在被使用！不能删除");
		}
	}

	@RequirePermission("model:getField;model:setField")
	@RequestMapping("getFieldListWithChecked/{modelId}")
	@ResponseBody
	public Result getFieldListWithChecked(int offset, int limit, String search,
			@PathVariable("modelId") String modelId) {
		PageInfo<DField> protocolList = this.dFieldService.getFieldListByModelIdAndPaged(offset / limit + 1, limit,
				modelId, search);
		return Result.succeed(protocolList);
	}

}
