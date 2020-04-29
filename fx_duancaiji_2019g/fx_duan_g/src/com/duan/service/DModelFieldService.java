package com.duan.service;

 
import com.duan.domain.DCom;
import com.duan.domain.DModelField;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

public interface  DModelFieldService  extends BaseService<DModelField>{
	
	PageInfo<DModelField> getListByPage(int currentNum, int pageSize, String name);

	void deleteBatchByIds(String[] idsStr);

	PageInfo<DModelField> findPageWithResult(String fieldname, String modelname, int currentNum, int pageSize);
}
