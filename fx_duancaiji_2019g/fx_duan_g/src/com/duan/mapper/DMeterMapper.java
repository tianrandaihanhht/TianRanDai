package com.duan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.duan.domain.DCollectConfig;
import com.duan.domain.DCom;
import com.duan.domain.DMeter;
import com.duan.domain.VModelField;

import tk.mybatis.mapper.common.Mapper;

public interface DMeterMapper  extends Mapper<DMeter> {

	DMeter getByName(String name);

	void deleteBatchByIds(@Param("ids")String[] idsStr);
	
	List<DMeter> findPageWithResult(@Param("name")String name,@Param("modelName") String modelname,@Param("comName") String comName,@Param("start") int currentNum, @Param("pageSize")int pageSize);

	void deleteCollectFieldByMeterId(@Param("meterId")String meterId);

	void addCollectFieldWithList(List<DCollectConfig> fieldlist);
	
}