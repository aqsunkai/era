package com.sun.permission.model;

import java.io.Serializable;

public class Role implements Serializable{
    /**
     * 生成的serialVersionUID
     */
    private static final long serialVersionUID = 4896534789126078526L;

    private Integer id;

    private String name;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}