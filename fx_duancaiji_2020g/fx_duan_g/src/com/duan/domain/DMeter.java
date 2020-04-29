package com.duan.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Transient;

@Table(name="d_meter")
public class DMeter implements Serializable{
   

	private static final long serialVersionUID = 1L;

	/**
	  * 主键
     */
	@Id
	@Column(name="id")
    private String id;
	
	private String modelid;
	
	private String comid;
    /**
     * mac地址
     */
    private String macaddress;

    /**
     * 名称
     */
    private String name;

    /**
     * 公司id
     */
    private String companyid;

    /**
     * 地址
     */
    private String address;
    
    private String protocolid;
    private String parserid;
    
    @Transient
    private String  fields;
    @Transient
    private List<VModelField> fieldlist;
	
    public String getProtocolid() {
		return protocolid;
	}

	public void setProtocolid(String protocolid) {
		this.protocolid = protocolid;
	}

	public String getParserid() {
		return parserid;
	}

	public void setParserid(String parserid) {
		this.parserid = parserid;
	}

	@Transient
    private DModel model=new DModel();
    /**
     * com口id
     */
    @Transient
    private DCom com=new DCom();

    /**
         * 是否可用 0 不可用 1可用
     */
    private Integer enable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public String getComid() {
		return comid;
	}

	public void setComid(String comid) {
		this.comid = comid;
	}

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DModel getModel() {
		return model;
	}

	public void setModel(DModel model) {
		this.model = model;
	}

	public DCom getCom() {
		return com;
	}

	public void setCom(DCom com) {
		this.com = com;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String  getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public List<VModelField> getFieldlist() {
		return fieldlist;
	}

	public void setFieldlist(List<VModelField> fieldlist) {
		this.fieldlist = fieldlist;
	}

   
    
}