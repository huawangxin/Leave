/*
 * BasicOvertimeServiceImpl.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hesc.leave.dao.BasicOvertimeDao;
import com.hesc.leave.pojo.BasicOvertime;
import com.hesc.leave.service.BasicOvertimeService;
import com.hesc.trundle.db.dao.BaseDao;
import com.hesc.trundle.db.page.Page;
import com.hesc.trundle.db.service.impl.BaseServiceImpl;

@Service("basicOvertimeService")
public class BasicOvertimeServiceImpl extends BaseServiceImpl<BasicOvertime> implements BasicOvertimeService {

    @Autowired
    private BasicOvertimeDao basicOvertimeDao;

    @SuppressWarnings("unchecked")
    @Override
    protected BaseDao<BasicOvertime> getBaseDao() {
        return (BaseDao<BasicOvertime>) basicOvertimeDao;
    }
    /**
	 * @param 根据用户名查找加班信息
	 * @param userName 用户名
	 * @return List<BasicOvertime> 返回加班实体类集合List<BasicOvertime>
	 */
	@Override
	public List<BasicOvertime> selectByName(String userName) {
		List<BasicOvertime> basicOvertimelist=basicOvertimeDao.selectByName(userName);
		return basicOvertimelist;
	}
	/**
	 * @param 根据月份查找加班信息
	 * @param basicLeave 
	 * @return List<BasicOvertime> 返回加班实体类集合List<BasicOvertime>
	 */
	@Override
	public List<BasicOvertime> selectListMonth(BasicOvertime basicOvertime) {
		List<BasicOvertime> basicOvertimelist=basicOvertimeDao.selectListMonth(basicOvertime);
		return basicOvertimelist;
	}
	/**
	 * @param 根据月份查找加班信息
	 * @param page 
	 * @return List<BasicLeave> 返回加班实体类集合List<BasicLeave>
	 */
	@Override
	public Page<BasicOvertime> selectPageListMonth(Page<BasicOvertime> page) {
		List<BasicOvertime> list=basicOvertimeDao.selectPageListMonth(page);
		page.getCount();
		page.getCurPage();
		page.getExecuteCount();
		page.getPageSize();
		page.getParams();
		page.setQueryList(list);
		page.getPageSize();
		return page;
	}
}