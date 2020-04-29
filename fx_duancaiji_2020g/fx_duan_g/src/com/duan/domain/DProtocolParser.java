package com.duan.domain;

import java.io.Serializable;

import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="d_protocol_parser")
public class DProtocolParser implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * id
     */
    private String id;

    /**
     * 协议id
     */
    private String protocolid;

    /**
     * 解析协议id
     */
    private String parserid;
    
	@Transient
    private DParser parser;

    public DParser getParser() {
		return parser;
	}

	public void setParser(DParser parser) {
		this.parser = parser;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}