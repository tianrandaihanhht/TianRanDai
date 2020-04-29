package com.duan.service;

import java.util.List;

 
import com.duan.domain.VCollectfield;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

public interface VCollectfieldService extends BaseService<VCollectfield>{

	List<VCollectfield> selectBycycle(Integer cycle);
	List<Integer>  selectDistinctcycle();
	
	PageInfo<VCollectfield> getListByPage(int i, int limit, String search);
	
	void deleteBatchByIds(String[] idsStr);
}
