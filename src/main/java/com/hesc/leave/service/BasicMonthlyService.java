/*
 * BasicMonthlyService.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.service;

import java.util.List;

import com.hesc.leave.pojo.BasicMonthly;
import com.hesc.trundle.db.service.BaseService;

public interface BasicMonthlyService extends BaseService<BasicMonthly> {
	/**
	 * @param 根据用户名查找加班信息
	 * @param userName 用户名
	 * @return List<BasicMonthly> 返回加班实体类集合List<BasicMonthly>
	 */
	public List<BasicMonthly> selectByName(String userName);

}