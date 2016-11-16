/*
 * BasicLeave.java
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
public class BasicLeave implements Identifiable  {

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
     * ****年*月*日*时
     */
    private String leaveStart;

    /**
     * ****年*月*日*时
     */
    private String leaveOver;

    /**
     * 精确到0.5天
     */
    private String leaveTime;

    /**
     * (事假，病假，公假，其他)
     */
    private String type;

    /**
     * 可选
     */
    private String description;

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
    public String getLeaveStart() {
        return leaveStart;
    }
    public void setLeaveStart(String leaveStart) {
        this.leaveStart = leaveStart == null ? null : leaveStart.trim();
    }
    public String getLeaveOver() {
        return leaveOver;
    }
    public void setLeaveOver(String leaveOver) {
        this.leaveOver = leaveOver == null ? null : leaveOver.trim();
    }
    public String getLeaveTime() {
        return leaveTime;
    }
    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime == null ? null : leaveTime.trim();
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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