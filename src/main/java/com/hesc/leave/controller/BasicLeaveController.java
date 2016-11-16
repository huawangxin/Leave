/*
 * BasicLeaveController.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.controller;

import java.io.IOException;
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

import com.hesc.leave.pojo.BasicLeave;
import com.hesc.leave.pojo.BasicUser;
import com.hesc.leave.service.BasicLeaveService;
import com.hesc.leave.service.BasicUserService;
import com.hesc.trundle.db.page.Page;

@Controller
@RequestMapping("/leave/basicleave")
public class BasicLeaveController {

    @Autowired
    private BasicLeaveService basicLeaveService;
    @Resource(name="basicUserService")
    private BasicUserService basicUserService;
    /**
     * 新增请假记录
     * @param model 请假实体
     * @return 请假记录页面
    */
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String add(Model model,HttpSession session){
        return "/leave/basicleave/add";
    }
    /**
     * @param 新增请假记录
     * @return 请假记录列表
     * @throws Exception 
    */
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String add(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
    	//获取请假参数
    	BasicLeave basicLeave=new BasicLeave();
    	String name=request.getParameter("name");
    	String department=request.getParameter("department");
    	String type=request.getParameter("type");
    	String leaveStart=request.getParameter("leaveStart");
    	String leaveOver=request.getParameter("leaveOver");
    	String description=request.getParameter("description");
        basicLeave.setName(name);
        basicLeave.setDepartment(department);
        basicLeave.setType(type);
        basicLeave.setLeaveStart(leaveStart);
        basicLeave.setLeaveOver(leaveOver);
        basicLeave.setDescription(description);
        //请假时长计算 格式yyyy年MM月dd日hh点
        String start= leaveStart;
        String over= leaveOver;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh点");
        long startSeconds = sdf.parse(start).getTime();//毫秒
        long overSeconds = sdf.parse(over).getTime();//毫秒
        long differ = Math.abs(overSeconds-startSeconds);
        //将时差精确到小时,并显示为 多少天（精度0.5天）
        differ=Math.round(differ/3600000);
        double days=differ/24;
        if((differ-days*24)>=12){
        	days=days+1;
        }else if((differ-days*24)>0){
        	days=days+0.5;
        }
        String leaveTime=Double.toString(days);
        basicLeave.setLeaveTime(leaveTime);
        //插如数据库
        basicLeaveService.insert(basicLeave);
        return "redirect:/leave/basicleave/list";
    }

    /**
     * 删除请假记录
     * @param id 请假记录ID
     * @return 请假记录列表
    */
    @RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
    public String delete(@PathVariable String id){
          basicLeaveService.deleteById(id);
          return "redirect:/leave/basicleave/list";
    }

    /**
     * 修改请假记录
     * @param id请假记录ID
     * @param model 请假记录
     * @return 请假记录修改页面
    */
    @RequestMapping(value="/{id}/update", method=RequestMethod.GET)
    public String update(@PathVariable String id, Model model,HttpSession session){
    	BasicLeave basicLeave=basicLeaveService.selectById(id);
    	session.setAttribute("basicLeave", basicLeave);
        model.addAttribute("basicLeave",basicLeave);
        return "/leave/basicleave/update";
    }
    /**
     * 修改请假记录
     * @param model 请假记录
     * @return 请假记录列表
    */
    @RequestMapping(value="/{id}/update", method=RequestMethod.POST)
    public String update(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
    	//获取请假参数
    	BasicLeave basicLeave=new BasicLeave();
    	String id=request.getParameter("id");
    	String leaveTime=request.getParameter("leaveTime");
    	String department=request.getParameter("department");
    	String type=request.getParameter("type");
    	String leaveStart=request.getParameter("leaveStart");
    	String leaveOver=request.getParameter("leaveOver");
    	String description=request.getParameter("description");
    	basicLeave.setId(id);
    	basicLeave.setLeaveTime(leaveTime);
        basicLeave.setDepartment(department);
        basicLeave.setType(type);
        basicLeave.setLeaveStart(leaveStart);
        basicLeave.setLeaveOver(leaveOver);
        basicLeave.setDescription(description);
        //请假时长计算 格式yyyy年MM月dd日hh点
        String start= leaveStart;
        String over= leaveOver;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh点");
        long startSeconds = sdf.parse(start).getTime();//毫秒
        long overSeconds = sdf.parse(over).getTime();//毫秒
        long differ = Math.abs(overSeconds-startSeconds);
        //将时差精确到小时,并显示为 多少天（精度0.5天）
        differ=Math.round(differ/3600000);
        double days=differ/24;
        if((differ-days*24)>=12){
        	days=days+0.5;
        }
        String leaveTime_1=Double.toString(days);
        basicLeave.setLeaveTime(leaveTime_1);
    	basicLeaveService.update(basicLeave);
        return "redirect:/leave/basicleave/list";
    }

    /**
     * 查看请假记录列表(个人)
     * @param model
     * @return 请假记录列表
    */
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String list(Model model,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	Page<BasicLeave> page_basicleave=new Page<BasicLeave>();
    	Map<String, Object> map_basicleave=new HashMap<>();
    	map_basicleave.put("name", basicUser.getName());
    	page_basicleave.setParams(map_basicleave);
    	page_basicleave.setPageSize(2);
    	page_basicleave=basicLeaveService.selectPageList(page_basicleave);
    	model.addAttribute("page_basicleave", page_basicleave);
      	model.addAttribute("basicLeaves", page_basicleave.getQueryList());
        return "/leave/basicleave/list";
    }
    /**
     * 下一页 和  尾页（个人）
     * @param model
     * @param req
     * @return 请假记录列表
     */
    @RequestMapping(value="/listnext1", method=RequestMethod.GET)
    public String listNext1(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
  	    int curPage=Integer.parseInt(req.getParameter("curPage"));
	    int pageCount=Integer.parseInt(req.getParameter("pageCount"));
      	Page<BasicLeave> page_basicleave=new Page<BasicLeave>();
      	page_basicleave.setPageSize(2);
      	if(curPage<pageCount){
      		page_basicleave.setCurPage(++curPage);
	    }else{
	    	page_basicleave.setCurPage(curPage);
	    }
      	Map<String, Object> map_basicleave=new HashMap<>();
  		map_basicleave.put("name", basicUser.getName());
  		page_basicleave.setParams(map_basicleave);
      	page_basicleave=basicLeaveService.selectPageList(page_basicleave);
      	session.setAttribute("page_basicleave", page_basicleave);
      	session.setAttribute("basicLeaves", page_basicleave.getQueryList());
      	model.addAttribute("page_basicleave", page_basicleave);
      	model.addAttribute("basicLeaves", page_basicleave.getQueryList());
      	return "/leave/basicleave/list";
    }
    /**
     * 上一页 和  首页（个人）
     * @param model
     * @param req
     * @return 请假记录列表
     */
    @RequestMapping(value="/listbefore1", method=RequestMethod.GET)
    public String listBefore1(Model model,HttpServletRequest req,HttpSession session){
        BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    //int pageCount=Integer.parseInt(req.getParameter("pageCount"));
        Page<BasicLeave> page_basicleave=new Page<BasicLeave>();
        page_basicleave.setPageSize(2);
        if(curPage>1){
        	page_basicleave.setCurPage(--curPage);
   	    }else{
   	    	page_basicleave.setCurPage(curPage);
   	    }
        Map<String, Object> map_basicleave=new HashMap<>();
    	map_basicleave.put("name", basicUser.getName());
    	page_basicleave.setParams(map_basicleave);
        //根据所属部门多少，使用不同的分页查询
        page_basicleave=basicLeaveService.selectPageList(page_basicleave);
        session.setAttribute("page_basicleave", page_basicleave);
        session.setAttribute("basicLeaves", page_basicleave.getQueryList());
        model.addAttribute("page_basicleave", page_basicleave);
        model.addAttribute("basicLeaves", page_basicleave.getQueryList());
        return "/leave/basicleave/list";
    }
    
    /**
     * 查看请假详情
     * @param id 请假ID
     * @param model 请假实体类
     * @return 请假某记录
    */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String show(@PathVariable String id, Model model){
          BasicLeave basicLeave = (BasicLeave)basicLeaveService.selectById(id);
          model.addAttribute("basicLeave",basicLeave);
          return "/leave/basicleave/show";
    }
    /**
     * 审核页面
     * @param id 审核请假列表
     * @param model 请假记录
     * @return 审核请假列表
    */
    @RequestMapping(value="/{id}/caudit", method=RequestMethod.GET)
    public String caudit(@PathVariable String id, Model model,HttpSession session){
    	BasicUser basicUser=basicUserService.selectById(id);
    	//根据所属部门数组
    	String[] departments=basicUser.getDepartment().split(",");
    	//查询某部门请假的记录，并按state,createTime排序
    	Page<BasicLeave> page_basicleave=new Page<BasicLeave>();
    	page_basicleave.setPageSize(5);
    	Map<String, Object> map_basicleave=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicleave.put("department"+i, departments[i]);
		}
		page_basicleave.setParams(map_basicleave);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicleave=basicLeaveService.selectPageListMonth(page_basicleave);
    	}else {
    		page_basicleave=basicLeaveService.selectPageList(page_basicleave);
		}
    	session.setAttribute("page_basicleave", page_basicleave);
    	session.setAttribute("basicLeaves", page_basicleave.getQueryList());
    	model.addAttribute("page_basicleave", page_basicleave);
    	model.addAttribute("basicLeaves", page_basicleave.getQueryList());
        return "/leave/basicleave/caudit";
    }
    /**
     * 下一页 和  尾页(审核)
     * @param model 请假实体
     * @return 审核请假列表
     */
    @RequestMapping(value="/listnext2", method=RequestMethod.GET)
    public String listNext2(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
  	    int curPage=Integer.parseInt(req.getParameter("curPage"));
	    int pageCount=Integer.parseInt(req.getParameter("pageCount"));
      	Page<BasicLeave> page_basicleave=new Page<BasicLeave>();
      	page_basicleave.setPageSize(5);
      	if(curPage<pageCount){
      		page_basicleave.setCurPage(++curPage);
	    }else{
	    	page_basicleave.setCurPage(curPage);
	    }
      //根据所属部门数组
    	String[] departments=basicUser.getDepartment().split(",");
    	//查询某部门请假的记录，并按state,createTime排序
    	Map<String, Object> map_basicleave=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicleave.put("department"+i, departments[i]);
		}
		page_basicleave.setParams(map_basicleave);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicleave=basicLeaveService.selectPageListMonth(page_basicleave);
    	}else {
    		page_basicleave=basicLeaveService.selectPageList(page_basicleave);
		}
      	session.setAttribute("page_basicleave", page_basicleave);
      	session.setAttribute("basicLeaves", page_basicleave.getQueryList());
      	model.addAttribute("page_basicleave", page_basicleave);
      	model.addAttribute("basicLeaves", page_basicleave.getQueryList());
      	return "/leave/basicleave/caudit";
    }
    /**
     * 上一页 和  首页（审核）
     * @param model 请假实体
     * @return 审核请假列表
     */
    @RequestMapping(value="/listbefore2", method=RequestMethod.GET)
    public String listBefore2(Model model,HttpServletRequest req,HttpSession session){
        BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    //int pageCount=Integer.parseInt(req.getParameter("pageCount"));
        Page<BasicLeave> page_basicleave=new Page<BasicLeave>();
        page_basicleave.setPageSize(5);
        if(curPage>1){
        	page_basicleave.setCurPage(--curPage);
   	    }else{
   	    	page_basicleave.setCurPage(curPage);
   	    }
        //根据所属部门数组
    	String[] departments=basicUser.getDepartment().split(",");
    	//查询某部门请假的记录，并按state,createTime排序
    	Map<String, Object> map_basicleave=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicleave.put("department"+i, departments[i]);
		}
		page_basicleave.setParams(map_basicleave);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicleave=basicLeaveService.selectPageListMonth(page_basicleave);
    	}else {
    		page_basicleave=basicLeaveService.selectPageList(page_basicleave);
		}
        session.setAttribute("page_basicleave", page_basicleave);
        session.setAttribute("basicLeaves", page_basicleave.getQueryList());
        model.addAttribute("page_basicleave", page_basicleave);
        model.addAttribute("basicLeaves", page_basicleave.getQueryList());
        return "/leave/basicleave/caudit";
    }
    /**
     * 审核 某一个请假单
     * @param id 请假ID
     * @param model 请假实体
     * @return 审核请假页面
    */
    @RequestMapping(value="/{id}/check", method=RequestMethod.GET)
    public String check(@PathVariable String id, Model model,HttpSession session){
    	BasicLeave basicLeave=basicLeaveService.selectById(id);
    	session.setAttribute("basicLeave", basicLeave);
        model.addAttribute("basicLeave",basicLeave);
        return "/leave/basicleave/check";
    }
    /**
     * 执行审核 某一个请假单
     * @param id 请假ID
     * @param model 请假实体
     * @return 审核请假列表
    */
    @RequestMapping(value="/{id}/check", method=RequestMethod.POST)
    public String check(@PathVariable String id,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
    	//判断是否同意
    	String state=request.getParameter("state");
    	BasicLeave basicLeave=new BasicLeave();
    	BasicUser basicUser=(BasicUser) session.getAttribute("basicUser");
    	basicLeave.setId(id);
    	if(state.equals("0")){
    		//一票否决权
    		basicLeave.setState("0");
    	}else {
    		//判断用户是组长，还是经理
        	String position=basicUser.getPosition();
        	if(position.equals("组长")){
            	basicLeave.setState("2");
        	}else if(position.equals("部门经理")){
            	basicLeave.setState("3");
        	}
		}
    	basicLeaveService.update(basicLeave);
        return "redirect:/leave/basicleave/"+basicUser.getId()+"/caudit";
    }
    /**
     * 下一页 和  尾页(月报)
     * @param model 请假实体
     * @return 请假月报审核列表
     */
    @RequestMapping(value="/listnext", method=RequestMethod.GET)
    public String listNext_BasicLeave(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
  	    int curPage=Integer.parseInt(req.getParameter("curPage"));
	    int pageCount=Integer.parseInt(req.getParameter("pageCount"));
      	//根据所属部门数组
      	String[] departments=basicUser.getDepartment().split(",");
      	/********************************************/
      	//查询某部门请假的记录，并按state,createTime排序
      	Page<BasicLeave> page_basicleave=new Page<BasicLeave>();
      	page_basicleave.setPageSize(2);
      	if(curPage<pageCount){
      		page_basicleave.setCurPage(++curPage);
	    }else{
	    	page_basicleave.setCurPage(curPage);
	    }
      	Map<String, Object> map_basicleave=new HashMap<>();
  		for(int i=0;i<departments.length;i++){
  			map_basicleave.put("department"+i, departments[i]);
  		}
  		map_basicleave.put("state", "3");
  		page_basicleave.setParams(map_basicleave);
      	//根据所属部门多少，使用不同的分页查询
      	if(departments.length>1){
      		page_basicleave=basicLeaveService.selectPageListMonth(page_basicleave);
      	}else {
      		page_basicleave=basicLeaveService.selectPageList(page_basicleave);
  		}
      	session.setAttribute("page_basicleave", page_basicleave);
      	session.setAttribute("basicLeaves", page_basicleave.getQueryList());
      	model.addAttribute("page_basicleave", page_basicleave);
      	model.addAttribute("basicLeaves", page_basicleave.getQueryList());
      	return "/leave/basicmonthly/caudit_leave";
    }
    /**
     * 上一页 和  首页(月报)
     * @param model 请假实体
     * @return 请假月报审核列表
     * @throws IOException 
     */
    @RequestMapping(value="/listbefore", method=RequestMethod.GET)
    public void listBefore(Model model,HttpServletRequest req,HttpServletResponse res,HttpSession session) throws IOException{
        BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    //int pageCount=Integer.parseInt(req.getParameter("pageCount"));
        //根据所属部门数组
        String[] departments=basicUser.getDepartment().split(",");
        /********************************************/
        //查询某部门请假的记录，并按state,createTime排序
        Page<BasicLeave> page_basicleave=new Page<BasicLeave>();
        page_basicleave.setPageSize(2);
        if(curPage>1){
        	page_basicleave.setCurPage(--curPage);
   	    }else{
   	    	page_basicleave.setCurPage(curPage);
   	    }
        Map<String, Object> map_basicleave=new HashMap<>();
    	for(int i=0;i<departments.length;i++){
    		map_basicleave.put("department"+i, departments[i]);
    	}
    	map_basicleave.put("state", "3");
    	page_basicleave.setParams(map_basicleave);
        //根据所属部门多少，使用不同的分页查询
        if(departments.length>1){
        	page_basicleave=basicLeaveService.selectPageListMonth(page_basicleave);
        }else {
        	page_basicleave=basicLeaveService.selectPageList(page_basicleave);
    	}
        session.setAttribute("page_basicleave", page_basicleave);
        session.setAttribute("basicLeaves", page_basicleave.getQueryList());
        model.addAttribute("page_basicleave", page_basicleave);
        model.addAttribute("basicLeaves", page_basicleave.getQueryList());
        session.setAttribute("basicLeavesSize", page_basicleave.getQueryList().size());
        StringBuilder s= new StringBuilder();
        s.append("<tr><th>");
        s.append("123");
        s.append("</th>");
        s.append("<th>");
        s.append("123");
        s.append("</th>");
        s.append("<th>");
        s.append("123");
        s.append("</th></tr>");
        s.append("<tr><td>");
        s.append("13");
         s.append("</td><td>");
         s.append("13");
         s.append("</td><td>");
         s.append("13");
         s.append("</td><td>");
         s.append("13");
         s.append("</td><td>");
        s.append("</td></tr>");
       
        res.getWriter().print(s);
       // return "/leave/basicmonthly/caudit_leave";
      //  return "success";
    }
    /**
     * 关键字查询
     * @param model 请假实体
     * @return 请假列表
     */
    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String search(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	//根据所属部门数组
    	String[] departments=basicUser.getDepartment().split(",");
    	//查询某部门请假的记录，并按state,createTime排序
    	Page<BasicLeave> page_basicleave=new Page<BasicLeave>();
    	List<BasicLeave> list=new ArrayList<>();
    	BasicLeave basicLeave=new BasicLeave();
    	basicLeave.setState("3");
    	//获取要查询的内容
    	String keyword=req.getParameter("keyword");
    	String year=req.getParameter("year");
    	String month=req.getParameter("month");
    	if(keyword.equals("")){
    		//只查年月
			String time="%"+year+"年"+month+"月%";
			basicLeave.setLeaveStart(time);
    	}else if(year.equals("")||month.equals("")){
			//只查关键字(只能是员工姓名)
    		basicLeave.setName("%"+keyword+"%");
		}else {
			String time="%"+year+"年"+month+"月%";
			basicLeave.setName("%"+keyword+"%");
			basicLeave.setLeaveStart(time);
		}
		for(int i=0;i<departments.length;i++){
			basicLeave.setDepartment(departments[i]);
			list.addAll(basicLeaveService.selectList(basicLeave));
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
    	session.setAttribute("page_basicleave", page_basicleave);
    	session.setAttribute("basicLeaves", list);
    	model.addAttribute("page_basicleave", page_basicleave);
    	model.addAttribute("basicLeaves", list);
        return "/leave/basicmonthly/caudit_leave";
    }
}