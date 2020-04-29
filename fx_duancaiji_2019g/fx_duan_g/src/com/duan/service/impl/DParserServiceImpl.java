package com.duan.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.domain.DCom;
import com.duan.domain.DParser;
import com.duan.domain.DProtocol;
import com.duan.mapper.DParserMapper;
import com.duan.mapper.DProtocolMapper;
import com.duan.service.DParserService;
import com.duan.service.DProtocolService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class DParserServiceImpl extends BaseServiceImpl<DParser> implements DParserService{


	@Autowired
	private DParserMapper dParserMapper;

	@Override
	protected Mapper<DParser> getMapper() {
		// TODO Auto-generated method stub
		return this.dParserMapper;
	}
	
	@Override
	public PageInfo<DParser> getListByPage(int currentNum, int pageSize, String name) {
		Example cond = new Example(DParser.class);
        if (!StringUtils.isEmpty(name)) {
        	try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("name",name);
        }
		
        PageHelper.startPage(currentNum, pageSize);
        List<DParser> list = this.dParserMapper.selectByExample(cond);
        
		return new PageInfo<DParser>(list);
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
		// TODO Auto-generated method stub
		
	}

 


}
