package com.duan.mapper;

import com.duan.domain.DCom;
import com.duan.domain.OpcConModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OpcConMapper extends Mapper<OpcConModel> {
	List<OpcConModel> selectByItemName(@Param("itemName") String itemName);

	OpcConModel getListByPage(String name);

	void addData(@Param("opcList") List<OpcConModel> opcList);

	void deleteBatchByIds(@Param("ids") String[] idsStr);
	List<OpcConModel>  findAllOpc();
}