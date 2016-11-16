/*
 * BasicMonthly.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.pojo;

import com.hesc.trundle.db.pojo.Identifiable;
/**
 * 
 * 
 * @author huawnagxin
 * @version 1.0 2015-11-17
 */
public class BasicMonthly implements Identifiable  {

    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 接收的部门
     */
    private String department;

    /**
     * 月报地址
     */
    private String url;

    /**
     * 所属月份
     */
    private String month;

    /**
     * 状态
     */
    private String state;

    /**
     * 排序,000-999
     */
    private String sort;

    /**
     * 创建时间
     */
    private Long createTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }
    public Long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}