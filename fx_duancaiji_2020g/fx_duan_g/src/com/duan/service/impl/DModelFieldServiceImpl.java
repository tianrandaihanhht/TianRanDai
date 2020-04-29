package com.duan.service.impl;

 
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.domain.DMeter;
import com.duan.domain.DModelField;
 
import com.duan.mapper.DModelFieldMapper;
 
import com.duan.service.DModelFieldService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class DModelFieldServiceImpl extends BaseServiceImpl<DModelField> 	implements DModelFieldService{

	@Autowired
	private DModelFieldMapper dModelFieldMapper;
	@Override
	protected Mapper<DModelField> getMapper() {
		// TODO Auto-generated method stub
		return this.dModelFieldMapper;
	}
	@Override
	public PageInfo<DModelField> getListByPage(int currentNum, int pageSize, String name) {
		Example cond = new Example(DModelField.class);
        if (!StringUtils.isEmpty(name)) {
        	try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("name",name);
        }
		
        PageHelper.startPage(currentNum, pageSize);
        List<DModelField> list = this.dModelFieldMapper.selectByExample(cond);
        
		return new PageInfo<DModelField>(list);
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
 
		this.dModelFieldMapper.deleteBatchByIds(idsStr);
	}

	@Override
	public PageInfo<DModelField> findPageWithResult(String fieldname, String modelname, int currentNum,int pageSize) {	 
		
	      PageHelper.startPage(currentNum, pageSize);
	      List<DModelField> list=   this.dModelFieldMapper.findPageWithResult(fieldname, modelname, currentNum, pageSize);
		  return new PageInfo<DModelField>(list);
	} 

}
