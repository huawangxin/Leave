/*
 * BasicUser.java
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
 * @author huawangxin
 * @version 1.0 2015-11-17
 */
public class BasicUser implements Identifiable  {

    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 所属部门(多个部门用,隔开)
     */
    private String department;

    /**
     * 职位(00,01,02)
     */
    private String position;

    /**
     * 电话
     */
    private String tel;

    /**
     * 默认0
     */
    private String remainTime;

    /**
     * 状态
     */
    private String state;

    /**
     * 排序,000-999
     */
    private String sort;

    /**
     * 创建日期
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }
    public String getRemainTime() {
        return remainTime;
    }
    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime == null ? null : remainTime.trim();
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