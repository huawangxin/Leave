/*
 * BasicOffdutyServiceImpl.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hesc.leave.dao.BasicOffdutyDao;
import com.hesc.leave.pojo.BasicOffduty;
import com.hesc.leave.service.BasicOffdutyService;
import com.hesc.trundle.db.dao.BaseDao;
import com.hesc.trundle.db.page.Page;
import com.hesc.trundle.db.service.impl.BaseServiceImpl;

@Service("basicOffdutyService")
public class BasicOffdutyServiceImpl extends BaseServiceImpl<BasicOffduty> implements BasicOffdutyService {

    @Autowired
    private BasicOffdutyDao basicOffdutyDao;

    @SuppressWarnings("unchecked")
    @Override
    protected BaseDao<BasicOffduty> getBaseDao() {
        return (BaseDao<BasicOffduty>) basicOffdutyDao;
    }
    /**
	 * @param 根据用户名查找调休信息
	 * @param userName 用户名
	 * @return List<BasicOffduty> 返回调休实体类集合List<BasicOffduty>
	 */
	@Override
	public List<BasicOffduty> selectByName(String userName) {
		List<BasicOffduty> basicOffdutylist=basicOffdutyDao.selectByName(userName);
		return basicOffdutylist;
	}
	/**
	 * @param 根据月份查找调休信息
	 * @param basicOffduty 
	 * @return List<BasicOffduty> 返回调休实体类集合List<BasicOffduty>
	 */
	@Override
	public List<BasicOffduty> selectListMonth(BasicOffduty basicOffduty) {
		List<BasicOffduty> basicOffdutylist=basicOffdutyDao.selectListMonth(basicOffduty);
		return basicOffdutylist;
	}
	/**
	 * @param 根据月份查找调休信息
	 * @param page 
	 * @return List<BasicLeave> 返回调休实体类集合List<BasicLeave>
	 */
	@Override
	public Page<BasicOffduty> selectPageListMonth(Page<BasicOffduty> page) {
		List<BasicOffduty> list=basicOffdutyDao.selectPageListMonth(page);
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