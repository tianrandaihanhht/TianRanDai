package com.duan.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Table(name = "v_model_field")
public class VModelField implements Serializable{
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
	
	private String modelid;

	private String fieldid;
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
	
	@Transient
	private String data1;
	@Transient
	private String data2;
	
    

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
    
    public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public String getFieldId() {
		return fieldid;
	}

	public void setFieldId(String fieldid) {
		this.fieldid = fieldid;
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