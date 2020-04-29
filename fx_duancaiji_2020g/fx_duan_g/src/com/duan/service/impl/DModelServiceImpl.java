package com.duan.service.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.Util.StringArrayUtil;
import com.duan.domain.DCom;
import com.duan.domain.DField;
import com.duan.domain.DModel;
import com.duan.domain.DProtocol;
import com.duan.mapper.DComMapper;
import com.duan.mapper.DModelMapper;
import com.duan.service.DModelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class DModelServiceImpl extends BaseServiceImpl<DModel> implements DModelService {

	@Autowired
	private DModelMapper dModelMapper;

	@Override
	protected Mapper<DModel> getMapper() {
		// TODO Auto-generated method stub
		return this.dModelMapper;
	}

	@Override
	public PageInfo<DModel> getListByPage(int currentNum, int pageSize, String name) {
		Example cond = new Example(DModel.class);
		if (!StringUtils.isEmpty(name)) {
//			try {
//				//name = new String(name.getBytes("iso8859-1"), "utf-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
			cond.createCriteria().andLike("name", name);
		}

		PageHelper.startPage(currentNum, pageSize);
		List<DModel> list = this.dModelMapper.selectByExample(cond);

		return new PageInfo<DModel>(list);
	}

	@Override
	public void saveModelProtocol(String  modelId, String protocolIdsStr) {
		// 解绑
		this.dModelMapper.deleteModelProtocolByModelId(modelId);
		
		if (StringUtils.isNotEmpty(protocolIdsStr)) {
			// 绑定
			String[] protocolIds = protocolIdsStr.split(",");
			List<Map<String,String>> params = new ArrayList<>(protocolIds.length);
	        Map<String,String> param = null;
	        for (String protocolId : protocolIds) {
	            param = new HashMap<>(2);
	            param.put("id", UUID.randomUUID().toString());
	            param.put("modelId",modelId);
	            param.put("protocolId",protocolId);
	            params.add(param);
	        }
			     
	        this.dModelMapper.saveModelProtocol(params);
		}
	}
	@Override
	public DModel getByName(String Name) {
		// TODO Auto-generated method stub
		return this.dModelMapper.getByName(Name);
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
		this.dModelMapper.deleteBatchByIds(idsStr);
	}

	@Override
	public void saveModelFields(String modelId, String fieIdsStr) {
	            
		        String[] fieldIds = fieIdsStr.split(",");
		        String[] oldfieldIds = this.dModelMapper.getFieldListByModelId(modelId);
		        
		        String[] add =    StringArrayUtil.minus(fieldIds,oldfieldIds);
		        String[] delete =    StringArrayUtil.minus(oldfieldIds,fieldIds);
		        
		        //刪除关联项
		        
		        
		        if(delete.length>0) {
		        	  this.dModelMapper.deleteModelFieldByIds(modelId,delete);
		        }	
		        
		        
		        // 解绑
				/*this.dModelMapper.deleteModelFieldIdsByModelId(modelId);*/	
				
				if (add.length>0) {
					// 绑定			
					List<Map<String,String>> params = new ArrayList<>(add.length);
			        Map<String,String> param = null;
			        for (String fieldId : add) {
			            param = new HashMap<>(2);
			            param.put("id",UUID.randomUUID().toString());
			            param.put("modelId",modelId);
			            param.put("fieldId",fieldId);
			            params.add(param);
			        }					     
			        this.dModelMapper.saveModelFields(params);
				}
	}
}
