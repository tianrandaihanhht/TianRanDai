package com.duan.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.domain.DParser;
import com.duan.domain.DProtocolParser;
import com.duan.service.DProtocolParserService;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.vo.Result;

@Controller
@RequestMapping("protocolparser")
public class DProtocolParserController {

	@Autowired
	private DProtocolParserService dProtocolParserService;

	@RequirePermission("protocol:listUI")
	@RequestMapping("listUI")
	public String listUI() {

		return "duan/protocol/listUI";
	}

	@RequirePermission("protocol:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit, String search) {
		PageInfo<DProtocolParser> pageInfo = this.dProtocolParserService.getListByPage(offset / limit + 1, limit,
				search);
		return Result.succeed(pageInfo);
	}

	@RequirePermission("protocol:listUI;meter:add;meter:update")
	@RequestMapping("findAlllist")
	@ResponseBody
	public Result findAlllist() {
		List<DProtocolParser> list = this.dProtocolParserService.getList();
		return Result.succeed(list);
	}
	// ===================================保存/修改/删除方法=======================================

	@RequestMapping("saveUI")
	public String saveUI(String id, HttpServletRequest request) {
		if (id != null) {
			DProtocolParser DProtocolParser = this.dProtocolParserService.getById(id);
			if (DProtocolParser != null) {
				request.setAttribute("protocol", DProtocolParser);
			}
		}
		return "duan/protocol/saveUI";
	}

	@RequirePermission("protocol:add;protocol:update")
	@RequestMapping("save")
	public String add(DProtocolParser DProtocolParser) {
		if (DProtocolParser.getId() != null && !DProtocolParser.getId().equals("")
				&& !DProtocolParser.getId().equals("null")) {
			this.dProtocolParserService.update(DProtocolParser);
		} else {
			DProtocolParser.setId(UUID.randomUUID().toString());
			this.dProtocolParserService.save(DProtocolParser);
		}
		return "redirect:listUI";

	}

	@RequirePermission("protocol:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {
		try {

			String[] idsStr = ids.split(",");
			if (idsStr.length == 1) {
				this.dProtocolParserService.deleteById(idsStr[0]);
			} else {
				this.dProtocolParserService.deleteBatchByIds(idsStr);
			}
			return Result.succeed();
		} catch (Exception ex) {
			return Result.fail(500, "该协议和解析关系数据正在被使用！不能删除");
		}
	}

	@RequirePermission("meter:add;meter:update")
	@RequestMapping("getProtocolParserListByProtocolId/{protocolId}")
	@ResponseBody
	public Result getProtocolParserListByProtocolId(@PathVariable("protocolId") String protocolId) {
		List<DProtocolParser> protocolList = this.dProtocolParserService.getProtocolParserListByProtocolId(protocolId);
		return Result.succeed(protocolList);
	}

}
