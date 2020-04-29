package com.duan.mapper;

import org.apache.ibatis.annotations.Param;

import com.duan.domain.DCollectConfig;
 

import tk.mybatis.mapper.common.Mapper;

public interface DCollectConfigMapper extends Mapper<DCollectConfig>{

	Object deleteBatchByIds(@Param("ids")String[] idsStr);

}