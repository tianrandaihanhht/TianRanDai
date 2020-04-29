package com.duan.service;

 
import com.duan.domain.DField;
import com.duan.domain.VModelField;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

public interface VModelFieldService extends BaseService<VModelField>{
 
	public PageInfo<VModelField> getFieldListWithCheckedAndPaged(int i, int limit, String meterId, String search) ; 
}
