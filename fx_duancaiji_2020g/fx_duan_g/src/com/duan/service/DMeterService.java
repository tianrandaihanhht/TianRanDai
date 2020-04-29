package com.duan.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.duan.domain.DCom;
import com.duan.domain.DField;
import com.duan.domain.DMeter;
import com.duan.domain.DModel;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

public interface DMeterService extends BaseService<DMeter>{
	
	PageInfo<DMeter> getListByPage(int currentNum, int pageSize, String name);

	DMeter getByName(String Name);

	void deleteBatchByIds(String[] idsStr);
	
	PageInfo<DMeter> findPageWithResult(@Param("name")String name,@Param("modelName") String modelname,@Param("comName") String comName,@Param("start") int currentNum, @Param("pageSize")int pageSize);

	void updateAndCollectField(DMeter dMeter);
	
}
