package com.duan.mapper;

 
import org.apache.ibatis.annotations.Param;

import com.duan.domain.DDictionary;

import tk.mybatis.mapper.common.Mapper;

public interface DDictionaryMapper extends Mapper<DDictionary> {

	DDictionary getByName(String portName);

	Object deleteBatchByIds(@Param("ids")String[] idsStr);
 
}