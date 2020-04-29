package com.duan.mapper;

 

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.duan.domain.DModelField;

import tk.mybatis.mapper.common.Mapper;

public interface DModelFieldMapper extends Mapper<DModelField> {

	void deleteBatchByIds(@Param("ids")String[] idsStr);

	List<DModelField> findPageWithResult(@Param("fieldname")String fieldname, @Param("modelname")String modelname, int currentNum, int pageSize);
	
}