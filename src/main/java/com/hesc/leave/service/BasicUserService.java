/*
 * BasicUserService.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.service;

import com.hesc.leave.pojo.BasicUser;
import com.hesc.trundle.db.service.BaseService;

public interface BasicUserService extends BaseService<BasicUser> {
	/**
	 * @param 根据用户名查找用户信息
	 * @param userName 用户名
	 * @return User 返回用户实体类User
	 */
	public BasicUser selectByName(String userName);
}