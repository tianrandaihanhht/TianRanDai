package com.duan.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.domain.DCom;
import com.duan.domain.DDictionary;
import com.duan.mapper.DComMapper;
import com.duan.mapper.DDictionaryMapper;
import com.duan.service.DComService;
import com.duan.service.DDictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class DDictionaryServiceImpl extends BaseServiceImpl<DDictionary> 	implements DDictionaryService{

	@Autowired
	private DDictionaryMapper dDictionaryMapper;
	
	@Override
	protected Mapper<DDictionary> getMapper() {
		// TODO Auto-generated method stub
		return this.dDictionaryMapper;
	}
	@Override
	public PageInfo<DDictionary> getListByPage(int currentNum, int pageSize, String name) {
		Example cond = new Example(DDictionary.class);
        if (!StringUtils.isEmpty(name)) {
        	try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("name",name);
        }
		
        PageHelper.startPage(currentNum, pageSize);
        List<DDictionary> list = this.dDictionaryMapper.selectByExample(cond);
        
		return new PageInfo<DDictionary>(list);
	}

	@Override
	public DDictionary getByName(String portName) {
		// TODO Auto-generated method stub
		return this.dDictionaryMapper.getByName(portName);
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
		// TODO Auto-generated method stub
		  this.dDictionaryMapper.deleteBatchByIds(idsStr); 
	}



}
