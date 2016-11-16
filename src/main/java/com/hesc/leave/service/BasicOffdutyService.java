/*
 * BasicOffdutyService.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.service;

import java.util.List;

import com.hesc.leave.pojo.BasicOffduty;
import com.hesc.trundle.db.page.Page;
import com.hesc.trundle.db.service.BaseService;

public interface BasicOffdutyService extends BaseService<BasicOffduty> {
	/**
	 * @param 根据用户名查找调休信息
	 * @param userName 用户名
	 * @return List<BasicOffduty> 返回调休实体类集合List<BasicOffduty>
	 */
	public List<BasicOffduty> selectByName(String userName);
	/**
	 * @param 根据月份查找调休信息
	 * @param basicLeave 
	 * @return List<BasicOffduty> 返回调休实体类集合List<BasicOffduty>
	 */
	public List<BasicOffduty> selectListMonth(BasicOffduty basicOffduty);
	/**
	 * @param 根据月份查找调休信息
	 * @param page 
	 * @return List<BasicLeave> 返回调休实体类集合List<BasicLeave>
	 */
	public Page<BasicOffduty> selectPageListMonth(Page<BasicOffduty> page);
}