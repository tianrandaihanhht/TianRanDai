package com.duan.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="d_model")
public class DModel implements Serializable{
  
 
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
     * 是 否大端序
     */
    private Integer isbigendian;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否默认
     */
    private Integer isdefault;

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

    public Integer getIsbigendian() {
        return isbigendian;
    }

    public void setIsbigendian(Integer isbigendian) {
        this.isbigendian = isbigendian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}