package com.duan.service;

 
import java.util.List;

import com.duan.domain.DProtocol;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

public interface  DProtocolService  extends BaseService<DProtocol>{

	PageInfo<DProtocol> getListByPage(int i, int limit, String search);

	void deleteBatchByIds(String[] idsStr);

	List<DProtocol> getProtocolListByModelId(String modelId);

	List<DProtocol> getProtocolListWithChecked(String modelId);

}
