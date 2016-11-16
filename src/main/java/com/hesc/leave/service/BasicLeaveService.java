/*
 * BasicLeaveService.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.service;

import java.util.List;

import com.hesc.leave.pojo.BasicLeave;
import com.hesc.trundle.db.page.Page;
import com.hesc.trundle.db.service.BaseService;

public interface BasicLeaveService extends BaseService<BasicLeave> {
	/**
	 * @param 根据用户名查找请假信息
	 * @param userName 用户名
	 * @return List<BasicLeave> 返回请假实体类集合List<BasicLeave>
	 */
	public List<BasicLeave> selectByName(String userName);
	/**
	 * @param 根据月份查找请假信息
	 * @param basicLeave 
	 * @return List<BasicLeave> 返回请假实体类集合List<BasicLeave>
	 */
	public List<BasicLeave> selectListMonth(BasicLeave basicLeave);
	/**
	 * @param 根据月份查找请假信息
	 * @param page 
	 * @return List<BasicLeave> 返回请假实体类集合List<BasicLeave>
	 */
	public Page<BasicLeave> selectPageListMonth(Page<BasicLeave> page);
}