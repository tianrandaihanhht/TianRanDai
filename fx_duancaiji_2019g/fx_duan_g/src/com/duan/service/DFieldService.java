package com.duan.service;

 
import java.util.List;

import com.duan.domain.DField;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

public interface DFieldService extends BaseService<DField>{
	
	PageInfo<DField> getListByPage(int currentNum, int pageSize, String name);

	DField getByName(String name);

	void deleteBatchByIds(String[] idsStr);
	
	List<DField> getFieldListByModelId(String modelId);

	PageInfo<DField> getFieldListByModelIdAndPaged(int i, int limit, String modelId,String search);

 
}
