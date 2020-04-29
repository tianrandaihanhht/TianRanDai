package com.duan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.duan.domain.VCollectfield;
import com.test.domain.Permission;

import tk.mybatis.mapper.common.Mapper;

public interface VCollectfieldMapper extends Mapper<VCollectfield> {
	List<VCollectfield> selectBycycle(int cycle);
	List<Integer>  selectDistinctcycle();
	void deleteBatchByIds(@Param("ids")String[] idsStr);
}
