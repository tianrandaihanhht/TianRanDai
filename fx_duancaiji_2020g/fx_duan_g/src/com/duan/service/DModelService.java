package com.duan.service;

import java.util.List;

import com.duan.domain.DCom;
import com.duan.domain.DField;
import com.duan.domain.DModel;
import com.duan.domain.DProtocol;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

public interface  DModelService extends BaseService<DModel>{
	
	PageInfo<DModel> getListByPage(int currentNum, int pageSize, String name);

	DModel getByName(String Name);

	void deleteBatchByIds(String[] idsStr);

	void saveModelProtocol(String modelId, String protocolIds);

	void saveModelFields(String modelId, String protocolIds);



 
}
