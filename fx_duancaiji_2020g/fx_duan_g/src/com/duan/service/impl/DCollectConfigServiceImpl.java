package com.duan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.domain.DCollectConfig;
 
import com.duan.mapper.DCollectConfigMapper;
 
import com.duan.service.DCollectConfigService;
 
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;


@Service
public class DCollectConfigServiceImpl extends BaseServiceImpl<DCollectConfig> 	implements DCollectConfigService{


	@Autowired
	private DCollectConfigMapper dCollectConfigMapper;
	@Override
	protected Mapper<DCollectConfig> getMapper() {
		// TODO Auto-generated method stub
		return this.dCollectConfigMapper;
	}
	@Override
	public void deleteBatchByIds(String[] idsStr) {
		
	  this.dCollectConfigMapper.deleteBatchByIds(idsStr);
		
	}

}
