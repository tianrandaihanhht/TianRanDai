package com.duan.mapper;

 
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.duan.domain.DProtocol;

import tk.mybatis.mapper.common.Mapper;

public interface DProtocolMapper extends Mapper<DProtocol>{

	void deleteBatchByIds(@Param("ids")String[] idsStr);

	List<DProtocol> getProtocolListByModelId(String modelId);
     
}