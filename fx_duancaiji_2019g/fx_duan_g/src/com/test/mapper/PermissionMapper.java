package com.test.mapper;

import java.io.Serializable;
import java.util.List;

import com.test.domain.Permission;

import tk.mybatis.mapper.common.Mapper;

public interface PermissionMapper extends Mapper<Permission>{

	/**
	 * 通过 用户ID 获取其对应的权限
	 * @param userId
	 * @return
	 */
	List<Permission> getPermissionList(Integer userId);

	int getChildrenCount(Serializable id);

	List<Permission> getPermissionWithoutButton();

	List<Permission> getPermissionListByRoleId(int roleId);

}
