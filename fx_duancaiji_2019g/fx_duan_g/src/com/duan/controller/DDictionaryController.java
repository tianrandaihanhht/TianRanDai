package com.duan.controller;

 
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

 
import com.duan.domain.DDictionary;
 
import com.duan.service.DDictionaryService;
 
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.vo.Result;

@Controller
@RequestMapping("dictionary")
public class DDictionaryController {
	 
		@Autowired
		private DDictionaryService dDictionaryService;
		
		@RequirePermission("dictionary:listUI")
		@RequestMapping("listUI")
		public String listUI() {
			
			return "duan/dictionary/listUI";
		}
		
		
		@RequirePermission("dictionary:listUI")
		@RequestMapping("list")
		@ResponseBody
		public Result list(int offset, int limit,String search) {
			PageInfo<DDictionary> pageInfo = this.dDictionaryService.getListByPage(offset / limit + 1, limit, search);
			return Result.succeed(pageInfo);
		}
		@RequirePermission("dictionary:listUI")
		@RequestMapping("findAlllist")
		@ResponseBody
		public Result findAlllist()
		{
		    List<DDictionary> list = this.dDictionaryService.getList();
			return Result.succeed(list);
		}
		//===================================保存/修改/删除方法=======================================	
		
			@RequestMapping("saveUI")
			public String saveUI(String id,HttpServletRequest request) {
				if (id != null) {
					DDictionary DDictionary = this.dDictionaryService.getById(id);
					if (DDictionary != null) {
						request.setAttribute("dictionary", DDictionary);
					}
				}
				return "duan/dictionary/saveUI";
			}

			@RequirePermission("dictionary:add;dictionary:update")
			@RequestMapping("save")
			public String add(DDictionary DDictionary) {
				if (DDictionary.getId() != null&&!DDictionary.getId().equals("")&&!DDictionary.getId().equals("null")) {
					this.dDictionaryService.update(DDictionary);
				} else {
					DDictionary.setId( UUID.randomUUID().toString());
					this.dDictionaryService.save(DDictionary);
				}
				return "redirect:listUI";
				
			}
			
			@RequirePermission("dictionary:delete")
			@RequestMapping("delete/{ids}")
			@ResponseBody
			public Result delete(@PathVariable("ids") String ids) {
				
				String[] idsStr = ids.split(",");
				if (idsStr.length == 1) {
					this.dDictionaryService.deleteById(idsStr[0]);
				} else {
					this.dDictionaryService.deleteBatchByIds(idsStr);
				}
				return Result.succeed();
			}
			
	
}
