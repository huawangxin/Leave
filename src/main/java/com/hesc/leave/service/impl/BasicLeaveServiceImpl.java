/*
 * BasicLeaveServiceImpl.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hesc.leave.dao.BasicLeaveDao;
import com.hesc.leave.pojo.BasicLeave;
import com.hesc.leave.service.BasicLeaveService;
import com.hesc.trundle.db.dao.BaseDao;
import com.hesc.trundle.db.page.Page;
import com.hesc.trundle.db.service.impl.BaseServiceImpl;

@Service("basicLeaveService")
public class BasicLeaveServiceImpl extends BaseServiceImpl<BasicLeave> implements BasicLeaveService {

    @Autowired
    private BasicLeaveDao basicLeaveDao;

    @SuppressWarnings("unchecked")
    @Override
    protected BaseDao<BasicLeave> getBaseDao() {
        return (BaseDao<BasicLeave>) basicLeaveDao;
    }
    /**
	 * @param 根据用户名查找请假信息
	 * @param userName 用户名
	 * @return BasicLeave 返回请假实体类BasicLeave
	 */
	@Override
	public List<BasicLeave> selectByName(String userName) {
		List<BasicLeave> basicLeavelist=basicLeaveDao.selectByName(userName);
		return basicLeavelist;
	}
	/**
	 * @param 根据月份查找请假信息
	 * @param basicLeave 
	 * @return List<BasicLeave> 返回请假实体类集合List<BasicLeave>
	 */;
	@Override
	public List<BasicLeave> selectListMonth(BasicLeave basicLeave) {
		List<BasicLeave> basicLeavelist=basicLeaveDao.selectListMonth(basicLeave);
		return basicLeavelist;
	}
	/**
	 * @param 根据月份查找请假信息
	 * @param page 分页实体
	 * @return List<BasicLeave> 返回请假实体类集合List<BasicLeave>
	 */
	@Override
	public Page<BasicLeave> selectPageListMonth(Page<BasicLeave> page){
		List<BasicLeave> list=basicLeaveDao.selectPageListMonth(page);
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