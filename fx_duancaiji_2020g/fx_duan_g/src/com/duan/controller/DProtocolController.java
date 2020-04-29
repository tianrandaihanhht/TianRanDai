package com.duan.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.domain.DProtocol;
import com.duan.service.DProtocolService;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.domain.Permission;
import com.test.vo.Result;

@Controller
@RequestMapping("protocol")
public class DProtocolController {

	 
		@Autowired
		private DProtocolService dProtocolService;
		
		@RequirePermission("protocol:listUI")
		@RequestMapping("listUI")
		public String listUI() {
			
			return "duan/protocol/listUI";
		}
		
		@RequirePermission("protocol:listUI")
		@RequestMapping("list")
		@ResponseBody
		public Result list(int offset, int limit,String search) {
			PageInfo<DProtocol> pageInfo = this.dProtocolService.getListByPage(offset / limit + 1, limit, search);
			return Result.succeed(pageInfo);
		}
		@RequirePermission("protocol:listUI;meter:add;meter:update")
		@RequestMapping("findAlllist")
		@ResponseBody
		public Result findAlllist()
		{
		    List<DProtocol> list = this.dProtocolService.getList();
			return Result.succeed(list);
		}
		//===================================保存/修改/删除方法=======================================	
		
			@RequestMapping("saveUI")
			public String saveUI(String id,HttpServletRequest request) {
				if (id != null) {
					DProtocol DProtocol = this.dProtocolService.getById(id);
					if (DProtocol != null) {
						request.setAttribute("protocol", DProtocol);
					}
				}
				return "duan/protocol/saveUI";
			}
			
			
			@RequirePermission("protocol:add;protocol:update")
			@RequestMapping("save")
			public String add(DProtocol DProtocol) {
				if (DProtocol.getId() != null&&!DProtocol.getId().equals("")&&!DProtocol.getId().equals("null")) {
					this.dProtocolService.update(DProtocol);
				} else {
					DProtocol.setId( UUID.randomUUID().toString());
					this.dProtocolService.save(DProtocol);
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
						this.dProtocolService.deleteById(idsStr[0]);
					} else {
						this.dProtocolService.deleteBatchByIds(idsStr);
					}
					return Result.succeed();
				}catch(Exception ex) 
				{
					return Result.fail(500, "该协议正在被使用！不能删除");
				}
		
			}
			@RequirePermission("model:setProtocol")
			@RequestMapping("getProtocolListWithChecked/{modelId}")
			@ResponseBody
			public Result getProtocolListWithChecked(@PathVariable("modelId") String  modelId) 
			{					 
					List<DProtocol> protocolList = this.dProtocolService.getProtocolListWithChecked(modelId);
					return Result.succeed(protocolList);				 
			}
			@RequirePermission("model:setProtocol;meter:add;meter:update")
			@RequestMapping("getProtocolListByModelId/{modelId}")
			@ResponseBody
			public Result getProtocolListByModelId(@PathVariable("modelId") String  modelId) 
			{	
				    if(modelId.equals( "null") ||"".equals(modelId)) {
				    	return	findAlllist();
				    }else {
						List<DProtocol> protocolList = this.dProtocolService.getProtocolListByModelId(modelId);
						return Result.succeed(protocolList);		
				    }
	 
		 
			}
}
