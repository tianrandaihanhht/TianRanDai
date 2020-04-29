package com.duan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.domain.DParser;
import com.duan.domain.DProtocolParser;
import com.duan.mapper.DProtocolParserMapper;
import com.duan.service.DProtocolParserService;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;

@Service
public class DProtocolParserServiceImpl extends BaseServiceImpl<DProtocolParser> implements DProtocolParserService{

	@Autowired
	private DProtocolParserMapper dProtocolParserMapper;
	
	@Override
	protected Mapper<DProtocolParser> getMapper() {
		// TODO Auto-generated method stub
		return this.dProtocolParserMapper;
	}

	
	@Override
	public PageInfo<DProtocolParser> getListByPage(int i, int limit, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
		// TODO Auto-generated method stub
		
	}

 

	@Override
	public List<DProtocolParser> getProtocolParserListByProtocolId(String protocolId) {
		// TODO Auto-generated method stub
		return dProtocolParserMapper.getProtocolParserListByProtocolId(protocolId);
	}


	@Override
	public List<DParser> getParserListByProtocolId(String protocolId) {
		// TODO Auto-generated method stub
		return dProtocolParserMapper.getParserListByProtocolId(protocolId);
	}

	
	
}
