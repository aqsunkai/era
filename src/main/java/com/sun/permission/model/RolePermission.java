package com.sun.permission.model;

import java.io.Serializable;

public class RolePermission implements Serializable{
    /**
     * 生成的serialVersionUID
     */
    private static final long serialVersionUID = 4898534789126078526L;

    private Integer id;

    private String rid;

    private String pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }
}