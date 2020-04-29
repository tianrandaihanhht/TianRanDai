package com.test.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.Util.LogUtil;
import com.test.domain.Permission;
import com.test.domain.User;
import com.test.service.PermissionService;
import com.test.service.UserService;
import com.test.vo.Result;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("login")
	@ResponseBody
	public Result login(String userName,String password, HttpSession session) {
		User user = this.userService.findUserByUserName(userName);
		
		if (user == null) {
			return Result.fail(404, "用户不存在");
		}
		
		if (!DigestUtils.md5Hex(password).equals(user.getPassword())) {
			return Result.fail(403, "用户密码不正确");
		}
		
		session.setAttribute("loginUser", user);
		
		return Result.succeed("manageUI");
	}
	
	@RequestMapping("logout")
	@ResponseBody
	public Result logout(HttpSession session) {
		LogUtil.print("测试重复请求11111");
		session.invalidate();
		return Result.succeed("/index.jsp");
	}
	
	
	@RequestMapping("manageUI")
	public String manageUI(HttpSession session) {
		
		Object obj = session.getAttribute("loginUser");
		if (obj == null) {
			return "redirect:/index.jsp";
		}
		
		User user = (User) obj;
		
        Map<String,List<Permission>> permissionMap = this.permissionService.getPermissionMapByUserId(user.getId());
		// （目录+菜单，封装了层级关系）
        user.setMenuList(permissionMap.get("menuList"));
        // （目录+菜单+按钮）
        user.setPermissionList(permissionMap.get("permissionList"));
        
		return "manageUI";
	}
}
