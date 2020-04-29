package com.duan.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.domain.DModelProtocol;

import com.duan.service.DModelProtocolService;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.vo.Result;

@Controller
@RequestMapping("modelprotocol")
public class DModelProtocolController {

	@Autowired
	private DModelProtocolService dModelProtocolService;

	@RequirePermission("modelprotocol:listUI")
	@RequestMapping("listUI")
	public String listUI() {

		return "duan/modelprotocol/listUI";
	}

	@RequirePermission("modelprotocol:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit, String search) {
		PageInfo<DModelProtocol> pageInfo = this.dModelProtocolService.getListByPage(offset / limit + 1, limit, search);
		return Result.succeed(pageInfo);
	}

	@RequirePermission("modelprotocol:listUI")
	@RequestMapping("findAlllist")
	@ResponseBody
	public Result findAlllist() {
		List<DModelProtocol> list = this.dModelProtocolService.getList();
		return Result.succeed(list);
	}
	// ===================================保存/修改/删除方法=======================================

	@RequestMapping("saveUI")
	public String saveUI(String id, HttpServletRequest request) {
		if (id != null) {
			DModelProtocol Dmp = this.dModelProtocolService.getById(id);
			if (Dmp != null) {
				request.setAttribute("modelprotocol", Dmp);
			}
		}
		return "duan/modelprotocol/saveUI";
	}

	@RequirePermission("modelprotocol:add;modelprotocol:update")
	@RequestMapping("save")
	public String add(DModelProtocol Dmp) {
		if (Dmp.getId() != null && !Dmp.getId().equals("") && !Dmp.getId().equals("null")) {
			this.dModelProtocolService.update(Dmp);
		} else {
			Dmp.setId(UUID.randomUUID().toString());
			this.dModelProtocolService.save(Dmp);
		}
		return "redirect:listUI";

	}

	@RequirePermission("modelprotocol:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {
		try {
			String[] idsStr = ids.split(",");
			if (idsStr.length == 1) {
				this.dModelProtocolService.deleteById(idsStr[0]);
			} else {
				this.dModelProtocolService.deleteBatchByIds(idsStr);
			}
			return Result.succeed();
		} catch (Exception ex) {
			return Result.fail(500, "该型号协议正在被使用！不能删除");
		}

	}
}
