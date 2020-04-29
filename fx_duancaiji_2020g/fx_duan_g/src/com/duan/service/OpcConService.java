package com.duan.service;

 
import com.duan.domain.DCom;
import com.duan.domain.OpcConModel;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OpcConService extends BaseService<OpcConModel>{
	PageInfo<OpcConModel> getListByPage(int currentNum, int pageSize, String serverIp,String lableId);

	String addData(String itemName, String serverIp, String userNameId, String passwordId);

	void deleteBatchByItemIds(String[] idsStr);

	List<OpcConModel> selectByItemName(String itemName);
	List<OpcConModel> findAllOpc();

}
