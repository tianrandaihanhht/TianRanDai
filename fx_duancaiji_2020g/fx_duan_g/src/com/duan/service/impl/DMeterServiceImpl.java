package com.duan.service.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duan.domain.DCollectConfig;
import com.duan.domain.DCom;
import com.duan.domain.DField;
import com.duan.domain.DMeter;
import com.duan.domain.DModel;
import com.duan.domain.VModelField;
import com.duan.mapper.DComMapper;
import com.duan.mapper.DMeterMapper;

import com.duan.service.DMeterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class DMeterServiceImpl extends BaseServiceImpl<DMeter> implements DMeterService {

	@Autowired
	private DMeterMapper dMeterMapper;

	@Override
	protected Mapper<DMeter> getMapper() {
		// TODO Auto-generated method stub
		return this.dMeterMapper;
	}

	@Override
	public PageInfo<DMeter> getListByPage(int currentNum, int pageSize, String name) {
		Example cond = new Example(DMeter.class);
		if (!StringUtils.isEmpty(name)) {
			try {
				name = new String(name.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			cond.createCriteria().andLike("name", name);
		}

		PageHelper.startPage(currentNum, pageSize);
		List<DMeter> list = this.dMeterMapper.selectByExample(cond);

		return new PageInfo<DMeter>(list);
	}

	@Override
	public DMeter getByName(String Name) {
		return this.dMeterMapper.getByName(Name);
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {

		this.dMeterMapper.deleteBatchByIds(idsStr);
	}

	@Override
	public PageInfo<DMeter> findPageWithResult(String name, String modelname, String comName, int currentNum,
			int pageSize) {

		PageHelper.startPage(currentNum, pageSize);
		List<DMeter> list = this.dMeterMapper.findPageWithResult(name, modelname, comName, currentNum, pageSize);
		return new PageInfo<DMeter>(list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateAndCollectField(DMeter dMeter) {
		update(dMeter);
		deleteCollectFieldByMeterId(dMeter.getId());
		List<DCollectConfig> instrlist = new ArrayList<DCollectConfig>();

		for (VModelField s : dMeter.getFieldlist()) {
			DCollectConfig dcc = new DCollectConfig();
			dcc.setId(UUID.randomUUID().toString());
			dcc.setMeterid(dMeter.getId());
			dcc.setFieldid(s.getFieldId());
			dcc.setCycle(Integer.parseInt(s.getDefaultcycle()));
			dcc.setFactor(s.getDefaultfactor());
			dcc.setUnit(s.getDefaultunit());
			dcc.setData1(s.getData1());
			dcc.setData2(s.getData2());
			dcc.setProtocolid(dMeter.getProtocolid());
			dcc.setParseid(dMeter.getParserid());
			instrlist.add(dcc);
		}
		if (instrlist.size() > 0) {
			addCollectField(instrlist);
		}
	}

	private void addCollectField(List<DCollectConfig> fieldlist) {

		this.dMeterMapper.addCollectFieldWithList(fieldlist);

	}

	private void deleteCollectFieldByMeterId(String meterId) {
		// TODO Auto-generated method stub
		this.dMeterMapper.deleteCollectFieldByMeterId(meterId);
	}

}
