/*
 * BasicOvertimeDao.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hesc.leave.pojo.BasicOvertime;
import com.hesc.trundle.db.dao.BaseDao;
import com.hesc.trundle.db.page.Page;

@Repository
public interface BasicOvertimeDao extends BaseDao<BasicOvertime> {
	/**
	 * @param 根据用户名查找加班信息
	 * @param userName 用户名
	 * @return List<BasicOvertime> 返回加班实体类集合List<BasicOvertime>
	 */
	public List<BasicOvertime> selectByName(String userName);
	/**
	 * @param 根据月份查找加班信息
	 * @param basicOvertime   加班实体
	 * @return List<BasicOvertime> 返回加班实体类集合List<BasicOvertime>
	 */
	public List<BasicOvertime> selectListMonth(BasicOvertime basicOvertime);
	/**
	 * @param 根据月份查找加班信息 分页
	 * @param page  分页实体
	 * @return Page<Object> 返回请假实体类集合Page<Object>
	 */
	public List<BasicOvertime> selectPageListMonth(Page<BasicOvertime> page);
}