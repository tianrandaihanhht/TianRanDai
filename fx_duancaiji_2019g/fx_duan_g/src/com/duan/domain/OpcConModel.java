package com.duan.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 从opc服务器读取数据放入本系统数据库
 *
 * @table datas_2_table
 */
@Table(name="d_opc_coninfo")
public class OpcConModel  implements Serializable {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="itemId")
    private String itemId;
    @Column(name="serverIp")
    private String serverIp;
    @Column(name="userName")
    private String userName;
    @Column(name="passWord")
    private String passWord;
    @Column(name="domainName")
    private String domainName;
    @Column(name="clsId")
    private String clsId;

    public OpcConModel() {
    }

    public OpcConModel(String id, String itemId, String serverIp, String userName, String passWord, String domainName, String clsId) {
        this.id = id;
        this.itemId = itemId;
        this.serverIp = serverIp;
        this.userName = userName;
        this.passWord = passWord;
        this.domainName = domainName;
        this.clsId = clsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getClsId() {
        return clsId;
    }

    public void setClsId(String clsId) {
        this.clsId = clsId;
    }
}
