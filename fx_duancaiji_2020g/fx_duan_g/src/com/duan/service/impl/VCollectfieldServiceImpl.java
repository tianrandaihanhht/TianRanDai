package com.duan.service.impl;



import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.domain.DCom;
import com.duan.domain.VCollectfield;
 
import com.duan.mapper.VCollectfieldMapper;
import com.duan.service.VCollectfieldService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class VCollectfieldServiceImpl extends BaseServiceImpl<VCollectfield> 	implements VCollectfieldService{
	
	@Autowired
	private VCollectfieldMapper vCollectfieldMapper;
	
	@Override
	protected Mapper<VCollectfield> getMapper() {
		// TODO Auto-generated method stub
		return this.vCollectfieldMapper;
	}

	@Override
	public List<VCollectfield> selectBycycle(Integer cycle) {
		return  this.vCollectfieldMapper.selectBycycle(cycle);	 
	}

	@Override
	public List<Integer> selectDistinctcycle() {
		// TODO Auto-generated method stub
		return this.vCollectfieldMapper.selectDistinctcycle();
	}

	@Override
	public PageInfo<VCollectfield> getListByPage(int currentNum, int pageSize, String name) {
		Example cond = new Example(VCollectfield.class);
        if (!StringUtils.isEmpty(name)) {
        	try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().orLike("fieldName",name);
            cond.createCriteria().orLike("meterName",name);
            cond.createCriteria().orLike("protocolName",name);
            cond.createCriteria().orLike("modelName",name);            
        }
		
        PageHelper.startPage(currentNum, pageSize);
        List<VCollectfield> list = this.vCollectfieldMapper.selectByExample(cond);
        
		return new PageInfo<VCollectfield>(list);
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
		// TODO Auto-generated method stub
		this.vCollectfieldMapper.deleteBatchByIds(idsStr);
	}
}
