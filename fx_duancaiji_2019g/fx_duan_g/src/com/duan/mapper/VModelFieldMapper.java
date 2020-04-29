package com.duan.mapper;

 
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.duan.domain.DField;
import com.duan.domain.VCollectfield;
import com.duan.domain.VModelField;

import tk.mybatis.mapper.common.Mapper;

public interface VModelFieldMapper extends Mapper<VModelField>{

	DField getByName(String name);

	void deleteBatchByIds(@Param("ids")String[] idsStr);

	List<VCollectfield> getFieldListByMeterId(String meterId);
    
}