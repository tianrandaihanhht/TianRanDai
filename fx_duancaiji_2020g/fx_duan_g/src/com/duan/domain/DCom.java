package com.duan.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="d_com")
public class DCom implements Serializable {
    
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
     * 名称
     */
    private String name;

    /**
     * 波特率
     */
    private Integer baudrate;

    /**
     * 校验位
     */
    private String parity;

    /**
     * 数据位
     */
    private Integer databits;

    /**
     * 停止位
     */
    private Integer stopbit;

    /**
     * 暂留
     */
    private String userid;

    /**
     * 是否默认
     */
    private Integer isdefault;

    /**
     * 串口名称
     */
    private String portname;

    /**
     * 备注
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(Integer baudrate) {
        this.baudrate = baudrate;
    }

    public String getParity() {
        return parity;
    }

    public void setParity(String parity) {
        this.parity = parity;
    }

    public Integer getDatabits() {
        return databits;
    }

    public void setDatabits(Integer databits) {
        this.databits = databits;
    }

    public Integer getStopbit() {
        return stopbit;
    }

    public void setStopbit(Integer stopbit) {
        this.stopbit = stopbit;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public String getPortname() {
        return portname;
    }

    public void setPortname(String portname) {
        this.portname = portname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }}