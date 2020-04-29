package com.duan.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="d_model_protocol")
public class DModelProtocol implements Serializable {
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

    private String protocolid;

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

    public String getProtocolid() {
        return protocolid;
    }

    public void setProtocolid(String protocolid) {
        this.protocolid = protocolid;
    }
}