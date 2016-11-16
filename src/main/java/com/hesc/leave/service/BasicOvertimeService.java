/*
 * BasicOvertimeService.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.service;

import java.util.List;

import com.hesc.leave.pojo.BasicOvertime;
import com.hesc.trundle.db.page.Page;
import com.hesc.trundle.db.service.BaseService;

public interface BasicOvertimeService extends BaseService<BasicOvertime> {
	/**
	 * @param 根据用户名查找请假信息
	 * @param userName 用户名
	 * @return List<BasicOvertime> 返回加班实体类集合List<BasicOvertime>
	 */
	public List<BasicOvertime> selectByName(String userName);
	/**
	 * @param 根据月份查找加班信息
	 * @param basicLeave 
	 * @return List<BasicOvertime> 返回加班实体类集合List<BasicOvertime>
	 */
	public List<BasicOvertime> selectListMonth(BasicOvertime basicOvertime);
	/**
	 * @param 根据月份查找加班信息
	 * @param page 
	 * @return List<BasicOvertime> 返回请假实体类集合List<BasicOvertime>
	 */
	public Page<BasicOvertime> selectPageListMonth(Page<BasicOvertime> page);
}