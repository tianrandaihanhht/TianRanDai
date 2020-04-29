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
import com.duan.domain.DProtocol;
import com.duan.service.DParserService;
import com.duan.service.DProtocolParserService;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.vo.Result;

@Controller
@RequestMapping("parser")
public class DParserController {

	@Autowired
	private DProtocolParserService dProtocolParserService;
	@Autowired
	private DParserService dParserService;

	@RequirePermission("parser:listUI")
	@RequestMapping("listUI")
	public String listUI() {

		return "duan/parser/listUI";
	}

	@RequirePermission("parser:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit, String search) {
		PageInfo<DParser> pageInfo = this.dParserService.getListByPage(offset / limit + 1, limit, search);
		return Result.succeed(pageInfo);
	}

	@RequirePermission("parser:listUI;meter:add;meter:update")
	@RequestMapping("findAlllist")
	@ResponseBody
	public Result findAlllist() {
		List<DParser> list = this.dParserService.getList();
		return Result.succeed(list);
	}
	// ===================================保存/修改/删除方法=======================================

	@RequestMapping("saveUI")
	public String saveUI(String id, HttpServletRequest request) {
		if (id != null) {
			DParser DParser = this.dParserService.getById(id);
			if (DParser != null) {
				request.setAttribute("parser", DParser);
			}
		}
		return "duan/parser/saveUI";
	}

	@RequirePermission("parser:add;parser:update")
	@RequestMapping("save")
	public String add(DParser DParser) {
		if (DParser.getId() != null && !DParser.getId().equals("") && !DParser.getId().equals("null")) {
			this.dParserService.update(DParser);
		} else {
			DParser.setId(UUID.randomUUID().toString());
			this.dParserService.save(DParser);
		}
		return "redirect:listUI";

	}

	@RequirePermission("parser:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {
		try {

			String[] idsStr = ids.split(",");
			if (idsStr.length == 1) {
				this.dParserService.deleteById(idsStr[0]);
			} else {
				this.dParserService.deleteBatchByIds(idsStr);
			}
			return Result.succeed();
		} catch (Exception ex) {
			return Result.fail(500, "该解析协议正在被使用！不能删除");
		}
	}

	@RequirePermission("meter:add;meter:update")
	@RequestMapping("getParserListByProtocolId/{protocolId}")
	@ResponseBody
	public Result getParserListByProtocolId(@PathVariable("protocolId") String protocolId) {
		if (protocolId.equals("null") || protocolId.length() <= 0) {
			return findAlllist();
		}
		List<DParser> protocolList = this.dProtocolParserService.getParserListByProtocolId(protocolId);
		return Result.succeed(protocolList);
	}

}
