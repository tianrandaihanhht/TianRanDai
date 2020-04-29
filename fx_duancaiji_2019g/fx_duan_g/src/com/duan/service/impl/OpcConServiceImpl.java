package com.duan.service.impl;

import com.duan.Util.x645.MD5Tools;
import com.duan.domain.DCom;
import com.duan.domain.OpcConModel;
import com.duan.mapper.DComMapper;
import com.duan.mapper.OpcConMapper;
import com.duan.service.DComService;
import com.duan.service.OpcConService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OpcConServiceImpl extends BaseServiceImpl<OpcConModel> 	implements OpcConService {
   
	@Autowired
	private OpcConMapper opcConMapper;
	@Override
	protected Mapper<OpcConModel> getMapper() {
		// TODO Auto-generated method stub
		return this.opcConMapper;
	}

	@Override
	public PageInfo<OpcConModel> getListByPage(int currentNum, int pageSize, String serverIp,String itemId) {
		Example cond = new Example(OpcConModel.class);
        if (!StringUtils.isEmpty(serverIp)) {
        	try {
				serverIp = new String(serverIp.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("serverIp",serverIp);
        }
		if (!StringUtils.isEmpty(itemId)) {
			try {
				itemId = new String(itemId.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			cond.createCriteria().andLike("itemId",itemId);
		}
		
        PageHelper.startPage(currentNum, pageSize);
        List<OpcConModel> list = this.opcConMapper.selectByExample(cond);
		return new PageInfo<OpcConModel>(list);
	}

	@Override
	public String addData(String itemName, String serverIp, String userNameId, String passwordId) {
		String [] itemNameAtrr= itemName.split(",");
		List<OpcConModel> opcList = new ArrayList<OpcConModel>();
		for(String  item:itemNameAtrr){
			//根据itemName查询是否存在
			List<OpcConModel> list = this.opcConMapper.selectByItemName(item);
			if(list.size()==0){
				OpcConModel o = new OpcConModel();
				o.setId(UUID.randomUUID()+"");
				o.setItemId(item);
				o.setServerIp(serverIp);
				o.setUserName(userNameId);
				o.setPassWord(MD5Tools.convertMD5(passwordId));
				opcList.add(o);
			}
		}
		if(opcList.size()>0){
			this.opcConMapper.addData(opcList);
			return "1";
		}else{
			return "0";
		}

	}

	@Override
	public void deleteBatchByItemIds(String[] idsStr) {
		this.opcConMapper.deleteBatchByIds(idsStr);
	}

	@Override
	public List<OpcConModel> selectByItemName(String itemName) {
		return this.opcConMapper.selectByItemName(itemName);
	}

	@Override
	public List<OpcConModel> findAllOpc() {
		return this.opcConMapper.findAllOpc();
	}


}
