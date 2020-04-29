package com.duan.service;

 
import com.duan.domain.DModelProtocol;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

public interface DModelProtocolService extends BaseService<DModelProtocol>{

	PageInfo<DModelProtocol> getListByPage(int i, int limit, String search);

	void deleteBatchByIds(String[] idsStr);

}
