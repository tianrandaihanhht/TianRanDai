package com.duan.mapper;

 
import java.util.List;

import com.duan.domain.DParser;
import com.duan.domain.DProtocolParser;

import tk.mybatis.mapper.common.Mapper;

public interface DProtocolParserMapper extends Mapper<DProtocolParser>{

	List<DProtocolParser> getProtocolParserListByProtocolId(String protocolId);

	List<DParser> getParserListByProtocolId(String protocolId);
 
}