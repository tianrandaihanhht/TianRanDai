package com.duan.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "v_collectfield")
public class VCollectfield implements Serializable{

 

	/**
	 * 主键
	 */
	@Id
	@Column(name = "id")
	private String id;
	private String meterid;
	
	private String comname;
	private int cycle;
	private float factor;
	private String metername;
	private String meteraddress;
	private int enable;
	private String modelname;
	private int isbigendian;
	private String fieldid;
	private String fieldname;
	private int inventedparametertype;
	private String protocolid;
	private String protocolname;
	private String parseid;
	private String parsename;
	private String data1;
	private String data2;
	private String unit;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getMeterid() {
		return meterid;
	}
	public void setMeterid(String meterid) {
		this.meterid = meterid;
	}
	
	public String getComName() {
		return comname;
	}
	public void setComName(String comName) {
		this.comname = comName;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	
	public float getFactor() {
		return factor;
	}
	public void setFactor(float factor) {
		this.factor = factor;
	}
	public String getMeterName() {
		return metername;
	}
	public void setMeterName(String meterName) {
		this.metername = meterName;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public String getModelName() {
		return modelname;
	}
	public void setModelName(String modelName) {
		this.modelname = modelName;
	}	
	public int getIsBigEndian() {
		return isbigendian;
	}
	public void setIsBigEndian(int isBigEndian) {
		this.isbigendian = isBigEndian;
	}
	public String getFieldId() {
		return fieldid;
	}
	public void setFieldId(String fieldId) {
		this.fieldid = fieldId;
	}
	public String getFieldName() {
		return fieldname;
	}
	public void setFieldName(String fieldName) {
		this.fieldname = fieldName;
	}
	
	public int getInventedParameterType() {
		return inventedparametertype;
	}
	public void setInventedParameterType(int inventedParameterType) {
		inventedparametertype = inventedParameterType;
	}
	public String getMeterAddress() {
		return meteraddress;
	}
	public void setMeterAddress(String meterAddress) {
		this.meteraddress = meterAddress;
	}
	public String getProtocolName() {
		return protocolname;
	}
	public void setProtocolName(String protocolName) {
		this.protocolname = protocolName;
	}	
	public String getParseId() {
		return parseid;
	}
	public void setParseId(String parseId) {
		this.parseid = parseId;
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String defaultUnit) {
		this.unit = defaultUnit;
	}
	public String getProtocolId() {
		return protocolid;
	}
	public void setProtocolId(String protocolId) {
		this.protocolid = protocolId;
	}
	public String getParseName() {
		return parsename;
	}
	public void setParseName(String parseName) {
		this.parsename = parseName;
	}
	
	
}
