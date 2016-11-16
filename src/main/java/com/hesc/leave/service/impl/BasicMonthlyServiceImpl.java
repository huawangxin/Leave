/*
 * BasicMonthlyServiceImpl.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.service.impl;

import java.util.List;

import com.hesc.leave.dao.BasicMonthlyDao;
import com.hesc.leave.pojo.BasicMonthly;
import com.hesc.leave.pojo.BasicOvertime;
import com.hesc.leave.service.BasicMonthlyService;
import com.hesc.trundle.db.dao.BaseDao;
import com.hesc.trundle.db.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("basicMonthlyService")
public class BasicMonthlyServiceImpl extends BaseServiceImpl<BasicMonthly> implements BasicMonthlyService {

    @Autowired
    private BasicMonthlyDao basicMonthlyDao;

    @SuppressWarnings("unchecked")
    @Override
    protected BaseDao<BasicMonthly> getBaseDao() {
        return (BaseDao<BasicMonthly>) basicMonthlyDao;
    }
    /**
	 * @param 根据用户名查找加班信息
	 * @param userName 用户名
	 * @return List<BasicMonthly> 返回加班实体类集合List<BasicMonthly>
	 */
	@Override
	public List<BasicMonthly> selectByName(String userName) {
		List<BasicMonthly> basicMonthlylist=basicMonthlyDao.selectByName(userName);
		return basicMonthlylist;
	}
}