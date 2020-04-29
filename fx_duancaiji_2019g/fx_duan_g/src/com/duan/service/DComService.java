package com.duan.service;

 
import com.duan.domain.DCom;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;
 

public interface DComService extends BaseService<DCom>{
	PageInfo<DCom> getListByPage(int currentNum, int pageSize, String name);

	DCom getByName(String portName);

	void deleteBatchByIds(String[] idsStr);
}
