/*
 * BasicMonthlyDao.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hesc.leave.pojo.BasicMonthly;
import com.hesc.trundle.db.dao.BaseDao;

@Repository
public interface BasicMonthlyDao extends BaseDao<BasicMonthly> {
	/**
	 * @param 根据用户名查找加班信息
	 * @param userName 用户名
	 * @return List<BasicMonthly> 返回加班实体类集合List<BasicMonthly>
	 */
	public List<BasicMonthly> selectByName(String userName);
}