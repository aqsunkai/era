package com.sun.permission.model;

import java.io.Serializable;

public class PermissionInit implements Serializable{
    /**
     * 生成的serialVersionUID
     */
    private static final long serialVersionUID = 4896534789126077681L;

    private Integer id;

    private String url;

    private String permissionInit;

    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPermissionInit() {
        return permissionInit;
    }

    public void setPermissionInit(String permissionInit) {
        this.permissionInit = permissionInit == null ? null : permissionInit.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}