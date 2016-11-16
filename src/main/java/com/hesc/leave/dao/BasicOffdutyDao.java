/*
 * BasicOffdutyDao.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hesc.leave.pojo.BasicOffduty;
import com.hesc.trundle.db.dao.BaseDao;
import com.hesc.trundle.db.page.Page;

@Repository
public interface BasicOffdutyDao extends BaseDao<BasicOffduty> {
	/**
	 * @param 根据用户名查找调休信息
	 * @param userName 用户名
	 * @return List<BasicOffduty> 返回调休实体类集合List<BasicOffduty>
	 */
	public List<BasicOffduty> selectByName(String userName);
	/**
	 * @param 根据月份查找调休信息
	 * @param basicOffduty 
	 * @return List<BasicLeave> 返回调休实体类集合List<BasicLeave>
	 */
	public List<BasicOffduty> selectListMonth(BasicOffduty basicOffduty);
	/**
	 * @param 根据月份查找调休信息 分页
	 * @param page 
	 * @return Page<Object> 返回调休实体类集合Page<Object>
	 */
	public List<BasicOffduty> selectPageListMonth(Page<BasicOffduty> page);
}