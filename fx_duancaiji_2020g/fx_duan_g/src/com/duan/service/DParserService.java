package com.duan.service;

import java.util.List;

import com.duan.domain.DParser;
import com.duan.domain.DProtocol;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

public interface DParserService  extends BaseService<DParser>{
	
	PageInfo<DParser> getListByPage(int i, int limit, String search);

	void deleteBatchByIds(String[] idsStr);

 

 
}
