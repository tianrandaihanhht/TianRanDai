package com.duan.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.domain.VCollectfield;
import com.duan.service.DCollectConfigService;
import com.duan.service.VCollectfieldService;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.vo.Result;

@Controller
@RequestMapping("collectField")
public class VCollectFieldController {

	@Autowired
	private DCollectConfigService dCollectConfigService;
	@Autowired
	private VCollectfieldService vCollectfieldService;

	@RequirePermission("collectField:listUI")
	@RequestMapping("listUI")
	public String listUI() {

		return "duan/vcollectfield/listUI";
	}

	@RequirePermission("collectField:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit, String search) {
		PageInfo<VCollectfield> pageInfo = this.vCollectfieldService.getListByPage(offset / limit + 1, limit, search);
		return Result.succeed(pageInfo);
	}

	@RequirePermission("collectField:listUI")
	@RequestMapping("findAlllist")
	@ResponseBody
	public Result findAlllist() {
		List<VCollectfield> list = this.vCollectfieldService.getList();
		return Result.succeed(list);
	}
	// ===================================保存/修改/删除方法=======================================

	@RequestMapping("saveUI")
	public String saveUI(String id, HttpServletRequest request) {
		if (id != null) {
			VCollectfield VCollectfield = this.vCollectfieldService.getById(id);
			if (VCollectfield != null) {
				request.setAttribute("collectField", VCollectfield);
			}
		}
		return "duan/vcollectfield/saveUI";
	}

	@RequirePermission("collectField:add;collectField:update")
	@RequestMapping("save")
	public String add(VCollectfield VCollectfield) {
		if (VCollectfield.getId() != null && !VCollectfield.getId().equals("")
				&& !VCollectfield.getId().equals("null")) {
			this.vCollectfieldService.update(VCollectfield);
		} else {
			VCollectfield.setId(UUID.randomUUID().toString());
			this.vCollectfieldService.save(VCollectfield);
		}
		return "redirect:listUI";

	}

	@RequirePermission("collectField:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {
		try {

			String[] idsStr = ids.split(",");
			if (idsStr.length == 1) {
				this.dCollectConfigService.deleteById(idsStr[0]);
			} else {
				this.dCollectConfigService.deleteBatchByIds(idsStr);
			}
			return Result.succeed();
		} catch (Exception ex) {
			return Result.fail(500, "该采集参数正在被使用！不能删除");
		}
	}
}
