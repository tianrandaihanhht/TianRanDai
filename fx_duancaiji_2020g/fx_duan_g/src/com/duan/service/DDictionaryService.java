package com.duan.service;

 
 
import com.duan.domain.DDictionary;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

public interface DDictionaryService extends BaseService<DDictionary>{

	PageInfo<DDictionary> getListByPage(int currentNum, int pageSize, String name);

	DDictionary getByName(String portName);

	void deleteBatchByIds(String[] idsStr);

}
