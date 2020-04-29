package com.duan.service;

import com.duan.domain.DCollectConfig;
 
import com.test.service.BaseService;

public interface DCollectConfigService extends BaseService<DCollectConfig>{

	void deleteBatchByIds(String[] idsStr);
		
}
