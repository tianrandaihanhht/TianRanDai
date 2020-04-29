package com.duan.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.duan.domain.DCom;
import com.duan.domain.DField;
import com.duan.domain.DModel;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.common.Mapper;

public interface DModelMapper  extends Mapper<DModel> {
	
	DModel getByName(String name);

	void deleteBatchByIds(@Param("ids")String[] idsStr);

	void saveModelProtocol(List<Map<String, String>> params);

	void deleteModelProtocolByModelId(String modelId);

	void saveModelFields(List<Map<String, String>> params);

	void deleteModelFieldIdsByModelId(String modelId);
	
	void deleteModelFieldByIds(@Param("modelId")String modelId,@Param("ids")String[] idsStr);
	
	
	String[]  getFieldListByModelId(String modelId);
 
}