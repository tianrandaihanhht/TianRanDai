package com.duan.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "d_field")
public class DField implements Serializable{
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
     * 默认单位
     */
    private String defaultunit;

    /**
     * 默认采集周期
     */
    private String defaultcycle;

    /**
     * 参数类型
     */
    private String paramtype;

    /**
     * 默认因子
     */
    private BigDecimal defaultfactor;

    /**
     * 虚拟参数类型
     */
    private Integer inventedparametertype;

	@Transient
	private Boolean checked;
	
    

    public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

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

    public String getDefaultunit() {
        return defaultunit;
    }

    public void setDefaultunit(String defaultunit) {
        this.defaultunit = defaultunit;
    }

    public String getDefaultcycle() {
        return defaultcycle;
    }

    public void setDefaultcycle(String defaultcycle) {
        this.defaultcycle = defaultcycle;
    }

    public String getParamtype() {
        return paramtype;
    }

    public void setParamtype(String paramtype) {
        this.paramtype = paramtype;
    }

    public BigDecimal getDefaultfactor() {
        return defaultfactor;
    }

    public void setDefaultfactor(BigDecimal defaultfactor) {
        this.defaultfactor = defaultfactor;
    }

    public Integer getInventedparametertype() {
        return inventedparametertype;
    }

    public void setInventedparametertype(Integer inventedparametertype) {
        this.inventedparametertype = inventedparametertype;
    }
}