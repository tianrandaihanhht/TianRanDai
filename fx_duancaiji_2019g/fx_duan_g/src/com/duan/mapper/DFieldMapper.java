package com.duan.mapper;

 
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.duan.domain.DField;

import tk.mybatis.mapper.common.Mapper;

public interface DFieldMapper extends Mapper<DField>{

	DField getByName(String name);

	void deleteBatchByIds(@Param("ids")String[] idsStr);

	List<DField> getFieldListByModelId(String modelId);
    
}