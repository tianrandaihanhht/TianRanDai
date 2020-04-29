package com.test.service.impl;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

import tk.mybatis.mapper.common.Mapper;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected abstract Mapper<T> getMapper();
	
	@Override
	public void save(T t) {
		getMapper().insert(t);
	}

	@Override
	public void update(T t) {
		getMapper().updateByPrimaryKeySelective(t);
	}

	@Override
	public void deleteById(Serializable id) {
		getMapper().deleteByPrimaryKey(id);
	}

	@Override
	public T getById(Serializable id) {
		return getMapper().selectByPrimaryKey(id);
	}

	@Override
	public List<T> getList() {
		return getMapper().selectAll();
	}

	@Override
	public PageInfo<T> getListByPage(int currentNum, int pageSize) {
		PageHelper.startPage(currentNum, pageSize);
		List<T> list = this.getList();
		return new PageInfo<T>(list);
	}

}
