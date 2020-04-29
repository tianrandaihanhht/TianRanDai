package com.duan.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

public class DCollectConfig implements Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
             * 主键
     */
	@Id
	@Column(name="id")
	private String id;

    /**
     * 仪表id
     */
    private String meterid;

    /**
     * 采集参数id
     */
    private String fieldid;

    /**
     * 采集周期
     */
    private Integer cycle;

    /**
     * 读数因子
     */
    private BigDecimal factor;

    /**
     * 默认单位
     */
    private String unit;

    /**
     * mudbus寄存器起始地址
     */
    private String data1;

    /**
     * 读取mudbus寄存器长度
     */
    private String data2;

    /**
     * 协议
     */
    private String protocolid;

    /**
     * 解析协议id
     */
    private String parseid;

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

    public String getFieldid() {
        return fieldid;
    }

    public void setFieldid(String fieldid) {
        this.fieldid = fieldid;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getProtocolid() {
        return protocolid;
    }

    public void setProtocolid(String protocolid) {
        this.protocolid = protocolid;
    }

    public String getParseid() {
        return parseid;
    }

    public void setParseid(String parseid) {
        this.parseid = parseid;
    }
}