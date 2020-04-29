package com.duan.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.duan.domain.DCom;
 
import com.duan.mapper.DComMapper;
 
import com.duan.service.DComService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class DComServiceImpl extends BaseServiceImpl<DCom> 	implements DComService{
   
	@Autowired
	private DComMapper dComMapper;
	@Override
	protected Mapper<DCom> getMapper() {
		// TODO Auto-generated method stub
		return this.dComMapper;
	}

	@Override
	public PageInfo<DCom> getListByPage(int currentNum, int pageSize, String name) {
		Example cond = new Example(DCom.class);
        if (!StringUtils.isEmpty(name)) {
        	try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("name",name);
        }
		if (!StringUtils.isEmpty(name)) {
			try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			cond.createCriteria().andLike("name",name);
		}
        PageHelper.startPage(currentNum, pageSize);
        List<DCom> list = this.dComMapper.selectByExample(cond);
        
		return new PageInfo<DCom>(list);
	}

	@Override
	public DCom getByName(String portName) {
		return this.dComMapper.getByName(portName);
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
		this.dComMapper.deleteBatchByIds(idsStr);
		
	}

}
