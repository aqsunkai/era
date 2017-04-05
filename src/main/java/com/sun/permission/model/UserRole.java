package com.sun.permission.model;

import java.io.Serializable;

public class UserRole implements Serializable{
    /**
     * 生成的serialVersionUID
     */
    private static final long serialVersionUID = 4898534789124562526L;

    private Integer id;

    private String uid;

    private String rid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }
}