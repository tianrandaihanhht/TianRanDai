package com.duan.service.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.domain.DCom;
import com.duan.domain.DModelProtocol;
 
import com.duan.mapper.DModelProtocolMapper;
import com.duan.service.DModelProtocolService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class DModelProtocolServiceImpl extends BaseServiceImpl<DModelProtocol> implements DModelProtocolService {

	@Autowired
	private DModelProtocolMapper dModelProtocolMapper;
	
	@Override
	protected Mapper<DModelProtocol> getMapper() {
		// TODO Auto-generated method stub
		return this.dModelProtocolMapper;
	}

	@Override
	public PageInfo<DModelProtocol> getListByPage(int currentNum, int pageSize, String modelId ) {
		Example cond = new Example(DCom.class);
        if (!StringUtils.isEmpty(modelId)) {
        	try {
        		modelId = new String(modelId.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("modelId",modelId);
        }
		
        PageHelper.startPage(currentNum, pageSize);
        List<DModelProtocol> list = this.dModelProtocolMapper.selectByExample(cond);
        
		return new PageInfo<DModelProtocol>(list);
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
		// TODO Auto-generated method stub
		
	}

	 

}
