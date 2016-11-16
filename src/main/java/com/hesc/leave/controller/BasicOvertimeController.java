/*
 * BasicOvertimeController.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hesc.leave.pojo.BasicOvertime;
import com.hesc.leave.pojo.BasicUser;
import com.hesc.leave.service.BasicOvertimeService;
import com.hesc.leave.service.BasicUserService;
import com.hesc.trundle.db.page.Page;

@Controller
@RequestMapping("/leave/basicovertime")
public class BasicOvertimeController {

    @Autowired
    private BasicOvertimeService basicOvertimeService;
    @Resource(name="basicUserService")
    private BasicUserService basicUserService;
    /**
     * 新增加班信息
     * @param model加班实体
     * @return 新增加班页面
     */
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String add(Model model){
          model.addAttribute("basicOvertime", new BasicOvertime());
          return "/leave/basicovertime/add";
    }
    /**
     * 新增加班信息
     * @param model加班实体
     * @return 加班列表
     */
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String add(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
    	//获取请假参数
    	BasicOvertime basicOvertime=new BasicOvertime();
    	String name=request.getParameter("name");
    	String department=request.getParameter("department");
    	String reason=request.getParameter("reason");
    	String ovetimeStart=request.getParameter("ovetimeStart");
    	String ovetimeOver=request.getParameter("ovetimeOver");
    	String place=request.getParameter("place");
    	basicOvertime.setName(name);
    	basicOvertime.setDepartment(department);
    	basicOvertime.setOvetimeStart(ovetimeStart);
    	basicOvertime.setOvetimeOver(ovetimeOver);
    	basicOvertime.setPlace(place);
    	basicOvertime.setReason(reason);
        //请假时长计算 格式yyyy年MM月dd日hh点
        String start= ovetimeStart;
        String over= ovetimeOver;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh点");
        long startSeconds = sdf.parse(start).getTime();//毫秒
        long overSeconds = sdf.parse(over).getTime();//毫秒
        long differ = Math.abs(overSeconds-startSeconds);
        //将时差精确到小时,并显示为 多少天（精度0.5天）
        differ=Math.round(differ/3600000);
        double days=differ/8;
        if((differ-days*8)>=4){
        	days=days+0.5;
        }
        String overtime=Double.toString(days);
        basicOvertime.setOvertime(overtime);
        //插如数据库
        basicOvertimeService.insert(basicOvertime);
        return "redirect:/leave/basicovertime/list";
    }

    /**
     * 删除加班记录
     * @param id 加班ID
     * @return 加班记录列表
    */
    @RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
    public String delete(@PathVariable String id){
          basicOvertimeService.deleteById(id);
          return "redirect:/leave/basicovertime/list";
    }

    /**
     * 修改加班信息
     * @param id加班ID
     * @param model 加班实体
     * @return 修改加班信息页面
    */
    @RequestMapping(value="/{id}/update", method=RequestMethod.GET)
    public String update(@PathVariable String id, Model model){
    	
        model.addAttribute(basicOvertimeService.selectById(id));
        return "/leave/basicovertime/update";
    }
    /**
     * 修改加班信息
     * @param id加班ID
     * @param model 加班实体
     * @return 加班列表页面
    */
    @RequestMapping(value="/{id}/update", method=RequestMethod.POST)
    public String update(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
    	//获取请假参数
    	BasicOvertime basicOvertime=new BasicOvertime();
    	String id=request.getParameter("id");
    	String overtime=request.getParameter("overtime");
    	String department=request.getParameter("department");
    	String reason=request.getParameter("reason");
    	String ovetimeStart=request.getParameter("ovetimeStart");
    	String ovetimeOver=request.getParameter("ovetimeOver");
    	String place=request.getParameter("place");
    	basicOvertime.setId(id);
    	basicOvertime.setOvertime(overtime);
    	basicOvertime.setDepartment(department);
    	basicOvertime.setOvetimeStart(ovetimeStart);
    	basicOvertime.setOvetimeOver(ovetimeOver);
    	basicOvertime.setPlace(place);
    	basicOvertime.setReason(reason);
        //请假时长计算 格式yyyy年MM月dd日hh点
        String start= ovetimeStart;
        String over= ovetimeOver;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh点");
        long startSeconds = sdf.parse(start).getTime();//毫秒
        long overSeconds = sdf.parse(over).getTime();//毫秒
        long differ = Math.abs(overSeconds-startSeconds);
        //将时差精确到小时,并显示为 多少天（精度0.5天）
        differ=Math.round(differ/3600000);
        double days=differ/8;
        if((differ-days*8)>=4){
        	days=days+0.5;
        }
        String overtime_1=Double.toString(days);
        basicOvertime.setOvertime(overtime_1);
        //插如数据库
        basicOvertimeService.update(basicOvertime);
        return "redirect:/leave/basicovertime/list";
    }

    /**
     * 查看加班列表
     * @param model 加班实体
     * @return 加班列表
    */
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String list(Model model,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	Page<BasicOvertime> page_basicOvertime=new Page<BasicOvertime>();
    	page_basicOvertime.setPageSize(2);
    	Map<String, Object> map_basicOvertime=new HashMap<>();
    	map_basicOvertime.put("name", basicUser.getName());
    	page_basicOvertime.setParams(map_basicOvertime);
    	page_basicOvertime=basicOvertimeService.selectPageList(page_basicOvertime);
        model.addAttribute("page_basicOvertime", page_basicOvertime);
    	model.addAttribute("basicOvertimes", page_basicOvertime.getQueryList());
        return "/leave/basicovertime/list";
    }
    /**
     * 下一页 和  尾页(个人)
     * @param model 加班实体
     * @return 加班列表
     */
    @RequestMapping(value="/listnext1", method=RequestMethod.GET)
    public String listNext1(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    int pageCount=Integer.parseInt(req.getParameter("pageCount"));
        Page<BasicOvertime> page_basicOvertime=new Page<BasicOvertime>();
    	page_basicOvertime.setPageSize(2);
      	if(curPage<pageCount){
      		page_basicOvertime.setCurPage(++curPage);
	    }else{
	    	page_basicOvertime.setCurPage(curPage);
	    }
    	Map<String, Object> map_basicOvertime=new HashMap<>();
		map_basicOvertime.put("name", basicUser.getName());
		page_basicOvertime.setParams(map_basicOvertime);
    	page_basicOvertime=basicOvertimeService.selectPageList(page_basicOvertime);
		model.addAttribute("page_basicOvertime", page_basicOvertime);
    	model.addAttribute("basicOvertimes", page_basicOvertime.getQueryList());
        return "/leave/basicovertime/list";
    }
    /**
     * 上一页 和  首页(个人)
     * @param model 加班实体
     * @return 加班列表
     */
    @RequestMapping(value="/listbefore1", method=RequestMethod.GET)
    public String listBefore1(Model model,HttpServletRequest req,HttpSession session){
        BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    //int pageCount=Integer.parseInt(req.getParameter("pageCount"));
        Page<BasicOvertime> page_basicOvertime=new Page<BasicOvertime>();
    	page_basicOvertime.setPageSize(2);
        if(curPage>1){
        	page_basicOvertime.setCurPage(--curPage);
   	    }else{
   	    	page_basicOvertime.setCurPage(curPage);
   	    }
    	Map<String, Object> map_basicOvertime=new HashMap<>();
		map_basicOvertime.put("name", basicUser.getName());
		page_basicOvertime.setParams(map_basicOvertime);
    	page_basicOvertime=basicOvertimeService.selectPageList(page_basicOvertime);
		model.addAttribute("page_basicOvertime", page_basicOvertime);
    	model.addAttribute("basicOvertimes", page_basicOvertime.getQueryList());
        return "/leave/basicovertime/list";
    }
    
    /**
     * 查看加班详情
     * @param id 加班ID
     * @param model 加班实体
     * @return 加班详情页
    */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String show(@PathVariable String id, Model model){
          BasicOvertime basicOvertime = (BasicOvertime)basicOvertimeService.selectById(id);
          model.addAttribute("basicOvertime",basicOvertime);
          return "/leave/basicovertime/show";
    }
    /**
     * 审核页面
     * @param id 加班ID
     * @param model 加班实体
     * @return 加班审核列表页面
    */
    @RequestMapping(value="/{id}/caudit", method=RequestMethod.GET)
    public String caudit(@PathVariable String id, Model model,HttpSession session){
    	BasicUser basicUser=basicUserService.selectById(id);
    	//根据所属部门数组
    	String[] departments=basicUser.getDepartment().split(",");
    	//查询某部门加班的记录，并按state,createTime排序
    	Page<BasicOvertime> page_basicOvertime=new Page<BasicOvertime>();
    	page_basicOvertime.setPageSize(5);
    	Map<String, Object> map_basicOvertime=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicOvertime.put("department"+i, departments[i]);
		}
		page_basicOvertime.setParams(map_basicOvertime);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicOvertime=basicOvertimeService.selectPageListMonth(page_basicOvertime);
    	}else {
    		page_basicOvertime=basicOvertimeService.selectPageList(page_basicOvertime);
		}
    	session.setAttribute("page_basicOvertime", page_basicOvertime);
    	session.setAttribute("basicOvertimes", page_basicOvertime.getQueryList());
    	model.addAttribute("page_basicOvertime", page_basicOvertime);
    	model.addAttribute("basicOvertimes", page_basicOvertime.getQueryList());
    	return "/leave/basicovertime/caudit";
    }
    /**
     * 下一页 和  尾页(审核下级)
     * @param model 加班实体
     * @return 加班审核页面
     */
    @RequestMapping(value="/listnext2", method=RequestMethod.GET)
    public String listNext2(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    int pageCount=Integer.parseInt(req.getParameter("pageCount"));
        //根据所属部门数组
        String[] departments=basicUser.getDepartment().split(",");
       //查询某部门加班的记录，并按state,createTime排序
    	Page<BasicOvertime> page_basicOvertime=new Page<BasicOvertime>();
    	page_basicOvertime.setPageSize(5);
      	if(curPage<pageCount){
      		page_basicOvertime.setCurPage(++curPage);
	    }else{
	    	page_basicOvertime.setCurPage(curPage);
	    }
    	Map<String, Object> map_basicOvertime=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicOvertime.put("department"+i, departments[i]);
		}
		page_basicOvertime.setParams(map_basicOvertime);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicOvertime=basicOvertimeService.selectPageListMonth(page_basicOvertime);
    	}else {
    		page_basicOvertime=basicOvertimeService.selectPageList(page_basicOvertime);
		}
    	session.setAttribute("page_basicOvertime", page_basicOvertime);
    	session.setAttribute("basicOvertimes", page_basicOvertime.getQueryList());
    	model.addAttribute("page_basicOvertime", page_basicOvertime);
    	model.addAttribute("basicOvertimes", page_basicOvertime.getQueryList());
        return "/leave/basicovertime/caudit";
    }
    /**
     * 上一页 和  首页(审核下级)
     * @param model 加班实体
     * @return 加班审核页面
     */
    @RequestMapping(value="/listbefore2", method=RequestMethod.GET)
    public String listBefore2(Model model,HttpServletRequest req,HttpSession session){
        BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    //int pageCount=Integer.parseInt(req.getParameter("pageCount"));
        //根据所属部门数组
        String[] departments=basicUser.getDepartment().split(",");
       //查询某部门加班的记录，并按state,createTime排序
    	Page<BasicOvertime> page_basicOvertime=new Page<BasicOvertime>();
    	page_basicOvertime.setPageSize(5);
        if(curPage>1){
        	page_basicOvertime.setCurPage(--curPage);
   	    }else{
   	    	page_basicOvertime.setCurPage(curPage);
   	    }
    	Map<String, Object> map_basicOvertime=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicOvertime.put("department"+i, departments[i]);
		}
		page_basicOvertime.setParams(map_basicOvertime);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicOvertime=basicOvertimeService.selectPageListMonth(page_basicOvertime);
    	}else {
    		page_basicOvertime=basicOvertimeService.selectPageList(page_basicOvertime);
		}
    	session.setAttribute("page_basicOvertime", page_basicOvertime);
    	session.setAttribute("basicOvertimes", page_basicOvertime.getQueryList());
    	model.addAttribute("page_basicOvertime", page_basicOvertime);
    	model.addAttribute("basicOvertimes", page_basicOvertime.getQueryList());
        return "/leave/basicovertime/caudit";
    }
    /**
     * 审核下级 加班
     * @param id加班ID
     * @param model 加班实体
     * @return 审核下级加班页面
    */
    @RequestMapping(value="/{id}/check", method=RequestMethod.GET)
    public String check(@PathVariable String id, Model model,HttpSession session){
    	BasicOvertime basicOvertime=basicOvertimeService.selectById(id);
    	session.setAttribute("basicOvertime", basicOvertime);
        model.addAttribute("basicOvertime",basicOvertime);
        return "/leave/basicovertime/check";
    }
    /**
     * 审核下级 加班
     * @param (该方法要想数据库中添加用户剩余时长)
     * @param id加班ID
     * @param model 加班实体
     * @return 审核加班列表页面
    */
    @RequestMapping(value="/{id}/check", method=RequestMethod.POST)
    public String check(@PathVariable String id,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
    	//判断是否同意
    	String name=request.getParameter("name");
    	String state=request.getParameter("state");
    	String overtime=request.getParameter("overtime");
    	BasicOvertime basicOvertime=new BasicOvertime();
    	BasicUser id_basicUser=new BasicUser();
    	id_basicUser=basicUserService.selectByName(name);
    	BasicUser basicUser=(BasicUser) session.getAttribute("basicUser");
    	basicOvertime.setId(id);
    	if(state.equals("0")){
    		//一票否决权
    		basicOvertime.setState("0");
        	basicOvertimeService.update(basicOvertime);
    	}else {
    		//判断用户是组长，还是经理
        	String position=basicUser.getPosition();
        	if(position.equals("组长")){
        		basicOvertime.setState("2");
            	basicOvertimeService.update(basicOvertime);
        	}else if(position.equals("部门经理")){
        		basicOvertime.setState("3");
            	basicOvertimeService.update(basicOvertime);
            	//向用户表中添加用户剩余时长
            	String ageTime=id_basicUser.getRemainTime();
            	double ageTime_1=Double.parseDouble(ageTime);
            	double overtime_1=Double.parseDouble(overtime);
            	double sumTime=ageTime_1+overtime_1;
            	String sumTime_1=Double.toString(sumTime);
            	id_basicUser.setRemainTime(sumTime_1);
            	basicUserService.update(id_basicUser);
        	}
		}
        return "redirect:/leave/basicovertime/"+basicUser.getId()+"/caudit";
    }
    /**
     * 下一页 和  尾页(加班月报审核)
     * @param model加班月报实体
     * @return 审核加班月报列表页面
     */
    @RequestMapping(value="/listnext", method=RequestMethod.GET)
    public String listNext_BasicLeave(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    int pageCount=Integer.parseInt(req.getParameter("pageCount"));
        //根据所属部门数组
        String[] departments=basicUser.getDepartment().split(",");
       //查询某部门加班的记录，并按state,createTime排序
    	Page<BasicOvertime> page_basicOvertime=new Page<BasicOvertime>();
    	page_basicOvertime.setPageSize(2);
      	if(curPage<pageCount){
      		page_basicOvertime.setCurPage(++curPage);
	    }else{
	    	page_basicOvertime.setCurPage(curPage);
	    }
    	Map<String, Object> map_basicOvertime=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicOvertime.put("department"+i, departments[i]);
		}
		map_basicOvertime.put("state", "3");
		page_basicOvertime.setParams(map_basicOvertime);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicOvertime=basicOvertimeService.selectPageListMonth(page_basicOvertime);
    	}else {
    		page_basicOvertime=basicOvertimeService.selectPageList(page_basicOvertime);
		}
    	session.setAttribute("page_basicOvertime", page_basicOvertime);
    	session.setAttribute("basicOvertimes", page_basicOvertime.getQueryList());
    	model.addAttribute("page_basicOvertime", page_basicOvertime);
    	model.addAttribute("basicOvertimes", page_basicOvertime.getQueryList());
        return "/leave/basicmonthly/caudit_overtime";
    }
    /**
     * 上一页 和  首页(加班月报审核)
     * @param model加班月报实体
     * @return 审核加班月报列表页面
     */
    @RequestMapping(value="/listbefore", method=RequestMethod.GET)
    public String listBefore(Model model,HttpServletRequest req,HttpSession session){
        BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    //int pageCount=Integer.parseInt(req.getParameter("pageCount"));
        //根据所属部门数组
        String[] departments=basicUser.getDepartment().split(",");
       //查询某部门加班的记录，并按state,createTime排序
    	Page<BasicOvertime> page_basicOvertime=new Page<BasicOvertime>();
    	page_basicOvertime.setPageSize(2);
        if(curPage>1){
        	page_basicOvertime.setCurPage(--curPage);
   	    }else{
   	    	page_basicOvertime.setCurPage(curPage);
   	    }
    	Map<String, Object> map_basicOvertime=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicOvertime.put("department"+i, departments[i]);
		}
		map_basicOvertime.put("state", "3");
		page_basicOvertime.setParams(map_basicOvertime);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicOvertime=basicOvertimeService.selectPageListMonth(page_basicOvertime);
    	}else {
    		page_basicOvertime=basicOvertimeService.selectPageList(page_basicOvertime);
		}
    	session.setAttribute("page_basicOvertime", page_basicOvertime);
    	session.setAttribute("basicOvertimes", page_basicOvertime.getQueryList());
    	model.addAttribute("page_basicOvertime", page_basicOvertime);
    	model.addAttribute("basicOvertimes", page_basicOvertime.getQueryList());
        return "/leave/basicmonthly/caudit_overtime";
    }
    /**
     * 关键字搜索
     * @param model 加班实体
     * @return  审核加班月报列表页面
     */
    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String search(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	Page<BasicOvertime> page_basicOvertime=new Page<>();
    	List<BasicOvertime> list=new ArrayList<>();
    	BasicOvertime basicOvertime=new BasicOvertime();
    	basicOvertime.setState("3");
    	//获取要查询的内容
    	String keyword=req.getParameter("keyword");
    	String year=req.getParameter("year");
    	String month=req.getParameter("month");
    	if(keyword.equals("")){
    		//只查年月
			String time="%"+year+"年"+month+"月%";
			basicOvertime.setOvetimeStart(time);
    	}else if(year.equals("")||month.equals("")){
			//只查关键字(只能是员工姓名)
    		basicOvertime.setName("%"+keyword+"%");
		}else {
    		basicOvertime.setName("%"+keyword+"%");
			String time="%"+year+"年"+month+"月%";
			basicOvertime.setOvetimeStart(time);
		}
    	//根据所属部门数组
    	String[] departments=basicUser.getDepartment().split(",");
		for(int i=0;i<departments.length;i++){
			basicOvertime.setDepartment(departments[i]);
			list.addAll(basicOvertimeService.selectList(basicOvertime));
		}
		//去重复
      	if(!list.isEmpty()){
            for(int i=0;i<list.size();i++){
                for(int j=list.size()-1;j>i;j--){
                    if(list.get(i).getName().equals(list.get(j).getName())){
                    	list.remove(j);
                    }
                }
            }
        }
    	model.addAttribute("page_basicOvertime", page_basicOvertime);
    	model.addAttribute("basicOvertimes", list);
        return "/leave/basicmonthly/caudit_overtime";
    }
}