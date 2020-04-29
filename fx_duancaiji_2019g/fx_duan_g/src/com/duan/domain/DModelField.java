package com.duan.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="d_model_field")
public class DModelField implements Serializable {

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
     * 型号id
     */
    private String modelid;
	
    @Transient
    private DModel model;

    /**
          * 参数id
     */
    private String fieldid;
	
    @Transient
    private DField field;
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

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getFieldid() {
        return fieldid;
    }

    public void setFieldid(String fieldid) {
        this.fieldid = fieldid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public DModel getModel() {
		return model;
	}

	public void setModel(DModel model) {
		this.model = model;
	}

	public DField getField() {
		return field;
	}

	public void setField(DField field) {
		this.field = field;
	}

}