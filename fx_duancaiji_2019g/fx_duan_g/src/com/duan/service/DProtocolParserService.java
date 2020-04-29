package com.duan.service;



 
import java.util.List;

import com.duan.domain.DParser;
import com.duan.domain.DProtocolParser;
import com.github.pagehelper.PageInfo;
import com.test.service.BaseService;

public interface  DProtocolParserService  extends BaseService<DProtocolParser>{

	PageInfo<DProtocolParser> getListByPage(int i, int limit, String search);

	void deleteBatchByIds(String[] idsStr);

	List<DProtocolParser> getProtocolParserListByProtocolId(String protocolId);
    
	List<DParser> getParserListByProtocolId(String protocolId);
}
 