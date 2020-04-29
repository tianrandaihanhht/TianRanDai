package com.duan.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.domain.DField;
import com.duan.domain.VCollectfield;
import com.duan.domain.VModelField;
 
import com.duan.mapper.VModelFieldMapper;
import com.duan.service.VModelFieldService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class VModelFieldServiceImpl extends BaseServiceImpl<VModelField> 	implements VModelFieldService{

	@Autowired
	private VModelFieldMapper vModelFieldMapper;
	
	@Override
	protected Mapper<VModelField> getMapper() {
		
		return this.vModelFieldMapper;
	}

	@Override
	public PageInfo<VModelField> getFieldListWithCheckedAndPaged(int currentNum, int pageSize, String meterId, String search) {
		
		Example cond = new Example(VModelField.class);
        if (!StringUtils.isEmpty(search)) {
        	try {
        		search = new String(search.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("modelid",search);
        }
		
 
		PageHelper.startPage(currentNum, pageSize);
        List<VModelField> list = this.vModelFieldMapper.selectByExample(cond); 
        //已经下放参数
		List<VCollectfield> list1 = this.vModelFieldMapper.getFieldListByMeterId(meterId);	
		
		for (VModelField field : list) {
			for (VCollectfield f : list1) {
				if (field.getFieldId().equals(f.getFieldId())) {
					field.setData1(f.getData1());
					field.setData2(f.getData2());
					field.setDefaultcycle(String.valueOf(f.getCycle()));
					field.setDefaultfactor(new BigDecimal(Float.toString(f.getFactor())));
					field.setDefaultunit(String.valueOf(f.getUnit()));
					field.setChecked(true);
				}
			}
		}
	
		
		return new PageInfo<VModelField>(list);
		
		
		 
	}

}
