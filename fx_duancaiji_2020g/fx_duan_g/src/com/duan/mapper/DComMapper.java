package com.duan.mapper;

import org.apache.ibatis.annotations.Param;

import com.duan.domain.DCom;

import tk.mybatis.mapper.common.Mapper;

public interface DComMapper extends Mapper<DCom> {

	DCom getByName(String name);

	void deleteBatchByIds(@Param("ids")String[] idsStr);
}