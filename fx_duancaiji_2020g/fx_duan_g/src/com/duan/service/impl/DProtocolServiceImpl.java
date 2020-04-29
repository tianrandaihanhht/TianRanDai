package com.duan.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.domain.DCom;
import com.duan.domain.DProtocol;
 
import com.duan.mapper.DProtocolMapper;
 
import com.duan.service.DProtocolService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.domain.Permission;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class DProtocolServiceImpl extends BaseServiceImpl<DProtocol> implements DProtocolService{

	@Autowired
	private DProtocolMapper dProtocolMapper;
	@Override
	protected Mapper<DProtocol> getMapper() {
 
		return this.dProtocolMapper;
	}
	
	@Override
	public PageInfo<DProtocol> getListByPage(int currentNum, int pageSize, String name) {
		Example cond = new Example(DCom.class);
        if (!StringUtils.isEmpty(name)) {
        	try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("name",name);
        }
		
        PageHelper.startPage(currentNum, pageSize);
        List<DProtocol> list = this.dProtocolMapper.selectByExample(cond);
        
		return new PageInfo<DProtocol>(list);
	}
	@Override
	public void deleteBatchByIds(String[] idsStr) {
		this.dProtocolMapper.deleteBatchByIds(idsStr);
		
	}

	@Override
	public List<DProtocol> getProtocolListWithChecked(String modelId) {
	 
		List<DProtocol> list = this.dProtocolMapper.getProtocolListByModelId(modelId);
		List<DProtocol> protocolList = this.getList();
		
		for (DProtocol protocol : protocolList) {
			for (DProtocol p : list) {
				if (protocol.getId().equals(p.getId())) {
					protocol.setChecked(true);
				}
			}
		}
		 
		return protocolList;
	}
	
	@Override
	public List<DProtocol> getProtocolListByModelId(String modelId) {
	 
		List<DProtocol> list = this.dProtocolMapper.getProtocolListByModelId(modelId);
	 
		return list;
	}

}
