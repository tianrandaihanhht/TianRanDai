package com.duan.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.domain.DCom;
import com.duan.domain.DField;
import com.duan.domain.DProtocol;
import com.duan.mapper.DComMapper;
import com.duan.mapper.DFieldMapper;
import com.duan.service.DComService;
import com.duan.service.DFieldService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class DFieldServiceImpl extends BaseServiceImpl<DField> 	implements DFieldService{

	@Autowired
	private DFieldMapper dFieldMapper;
	@Override
	protected Mapper<DField> getMapper() {
		// TODO Auto-generated method stub
		return this.dFieldMapper;
	}
	@Override
	public PageInfo<DField> getListByPage(int currentNum, int pageSize, String name) {
		Example cond = new Example(DField.class);
        if (!StringUtils.isEmpty(name)) {
        	try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("name",name);
        }
		
        PageHelper.startPage(currentNum, pageSize);
        List<DField> list = this.dFieldMapper.selectByExample(cond);
        
		return new PageInfo<DField>(list);
	}

	@Override
	public DField getByName(String name) {
		// TODO Auto-generated method stub
		return this.dFieldMapper.getByName(name);
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
		this.dFieldMapper.deleteBatchByIds(idsStr);		
	}
	
	@Override
	public PageInfo<DField> getFieldListByModelIdAndPaged(int currentNum, int pageSize,String modelId,String search) {

		
		Example cond = new Example(DField.class);
        if (!StringUtils.isEmpty(search)) {
        	try {
        		search = new String(search.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("name",search);
        }
		
 
		PageHelper.startPage(currentNum, pageSize);
        List<DField> list = this.dFieldMapper.selectByExample(cond);        	
		List<DField> list1 = this.dFieldMapper.getFieldListByModelId(modelId);	
		
		for (DField field : list) {
			for (DField f : list1) {
				if (field.getId().equals(f.getId())) {
					field.setChecked(true);
				}
			}
		}
	
		
		return new PageInfo<DField>(list);
	}
	@Override
	public List<DField> getFieldListByModelId(String modelId) {
		// TODO Auto-generated method stub
		return this.dFieldMapper.getFieldListByModelId(modelId);
	}
	 

}
