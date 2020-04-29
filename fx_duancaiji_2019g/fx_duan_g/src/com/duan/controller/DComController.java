package com.duan.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.Util.SerialPortManager;
import com.duan.domain.DCom;
import com.duan.domain.DModel;
import com.duan.service.DComService;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;

import com.test.vo.Result;

@Controller
@RequestMapping("com")
public class DComController {

	@Autowired
	private DComService dComService;

	@RequirePermission("com:listUI")
	@RequestMapping("listUI")
	public String listUI() {

		return "duan/com/listUI";
	}

	@RequirePermission("com:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit, String search) {
		PageInfo<DCom> pageInfo = this.dComService.getListByPage(offset / limit + 1, limit, search);
		return Result.succeed(pageInfo);
	}

	@RequirePermission("com:listUI")
	@RequestMapping("findAlllist")
	@ResponseBody
	public Result findAlllist() {
		List<DCom> list = this.dComService.getList();
		return Result.succeed(list);
	}
	// ===================================保存/修改/删除方法=======================================

	@RequestMapping("saveUI")
	public String saveUI(String id, HttpServletRequest request) {
		if (id != null) {
			DCom DCom = this.dComService.getById(id);
			if (DCom != null) {
				request.setAttribute("com", DCom);
			}
		}
		return "duan/com/saveUI";
	}

	@RequirePermission("com:add;com:update")
	@RequestMapping("findLocalComList")
	@ResponseBody
	public Result findLocalComList() {
		ArrayList<String> ports = SerialPortManager.findPort(); // 获取本机所有串口
		return Result.succeed(ports);
	}

	@RequirePermission("com:add;com:update")
	@RequestMapping("save")
	public String add(DCom DCom) {

		try {

			if (DCom.getId() != null && !DCom.getId().equals("") && !DCom.getId().equals("null")) {
				this.dComService.update(DCom);
			} else {
				DCom.setId(UUID.randomUUID().toString());
				this.dComService.save(DCom);
			}
			return "redirect:listUI";
		} catch (Exception ex) {
			return "redirect:listUI";
		}

	}

	@RequirePermission("com:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {
		try {

			String[] idsStr = ids.split(",");
			if (idsStr.length == 1) {
				this.dComService.deleteById(idsStr[0]);
			} else {
				this.dComService.deleteBatchByIds(idsStr);
			}
			return Result.succeed();
		} catch (Exception ex) {
			return Result.fail(500, "串口上有连接的仪表！不能删除");
		}
	}

}
