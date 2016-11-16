/*
 * BasicUserServiceImpl.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.service.impl;

import com.hesc.leave.dao.BasicUserDao;
import com.hesc.leave.pojo.BasicUser;
import com.hesc.leave.service.BasicUserService;
import com.hesc.trundle.db.dao.BaseDao;
import com.hesc.trundle.db.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("basicUserService")
public class BasicUserServiceImpl extends BaseServiceImpl<BasicUser> implements BasicUserService {

    @Autowired
    private BasicUserDao basicUserDao;

    @SuppressWarnings("unchecked")
    @Override
    protected BaseDao<BasicUser> getBaseDao() {
        return (BaseDao<BasicUser>) basicUserDao;
    }
    /**
	 * @param 根据用户名查找用户信息
	 * @param userName 用户名
	 * @return User 返回用户实体类User
	 */
	@Override
	public BasicUser selectByName(String userName) {
		BasicUser basicUser=basicUserDao.selectByName(userName);
		return basicUser;
	}
}