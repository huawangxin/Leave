/*
 * BasicOffdutyController.java
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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hesc.leave.pojo.BasicOffduty;
import com.hesc.leave.pojo.BasicUser;
import com.hesc.leave.service.BasicOffdutyService;
import com.hesc.leave.service.BasicUserService;
import com.hesc.trundle.db.page.Page;

@Controller
@RequestMapping("/leave/basicoffduty")
public class BasicOffdutyController {

    @Autowired
    private BasicOffdutyService basicOffdutyService;
    @Resource(name="basicUserService")
    private BasicUserService basicUserService;
    /**
     * 新增调休
     * @param model调休实体
     * @return 新增调休页面
    */
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String add(Model model){
          //model.addAttribute("basicOffduty", new BasicOffduty());
          return "/leave/basicoffduty/add";
    }
    /**
     * 新增调休
     * @param model调休实体
     * @return 调休列表页面
    */
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String add(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
    	//获取用户的剩余时长
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	basicUser=basicUserService.selectById(basicUser.getId());
    	String remainTime=basicUser.getRemainTime();
    	double remainTimes=Double.parseDouble(remainTime);
    	//获取请假参数
    	BasicOffduty basicOffduty=new BasicOffduty();
    	String name=request.getParameter("name");
    	String department=request.getParameter("department");
    	String reason=request.getParameter("reason");
    	String ovetimeStart=request.getParameter("ovetimeStart");
    	String ovetimeOver=request.getParameter("ovetimeOver");
    	basicOffduty.setName(name);
    	basicOffduty.setDepartment(department);
    	basicOffduty.setOvetimeOver(ovetimeOver);
    	basicOffduty.setOvetimeStart(ovetimeStart);
    	basicOffduty.setReason(reason);
        //请假时长计算 格式yyyy年MM月dd日hh点
        String start= ovetimeStart;
        String over= ovetimeOver;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh点");
        long startSeconds = sdf.parse(start).getTime();//毫秒
        long overSeconds = sdf.parse(over).getTime();//毫秒
        long differ = Math.abs(overSeconds-startSeconds);
        //将时差精确到小时,并显示为 多少天（精度0.5天）
        differ=Math.round(differ/3600000);
        double days=differ/24;
        if((differ-days*24)>8){
        	days=days+1.0;
        }else if((differ-days*24)>4){
        	days=days+0.5;
        }
        //判断是否大于用户剩余可用时长
        if(days>remainTimes){
        	//返回新增页面页面，显示错误信息
        	request.setAttribute("error", "剩余时长不够，请修改调休时间 !");
        	return "/leave/basicoffduty/add";
        }
        String offdutyTime=Double.toString(days);
        basicOffduty.setOvertime(offdutyTime);
        //插如数据库
    	basicOffdutyService.insert(basicOffduty);
        return "redirect:/leave/basicoffduty/list";
    }

    /**
     * 删除调休记录
     * @param id调休ID
     * @return 调休记录列表
    */
    @RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
    public String delete(@PathVariable String id){
          basicOffdutyService.deleteById(id);
          return "redirect:/leave/basicoffduty/list";
    }

    /**
     * 修改调休信息
     * @param id调休记录ID
     * @param model 调休实体
     * @return 修改调休页面
    */
    @RequestMapping(value="/{id}/update", method=RequestMethod.GET)
    public String update(@PathVariable String id, Model model,HttpSession session){
    	BasicOffduty basicOffduty=basicOffdutyService.selectById(id);
    	session.setAttribute("basicOffduty", basicOffduty);
        model.addAttribute("basicLeave",basicOffduty);
        return "/leave/basicoffduty/update";
    }
    /**
     * 修改调休信息
     * @param model 调休实体
     * @return 调休列表页面
    */
    @RequestMapping(value="/{id}/update", method=RequestMethod.POST)
    public String update(@Valid BasicOffduty basicOffduty, BindingResult result,HttpServletRequest request,HttpSession session) throws Exception{
          if(result.hasErrors()){
                return "/leave/basicoffduty/update";
          }
        //获取用户的剩余时长
      	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
      	String remainTime=basicUser.getRemainTime();
      	double remainTimes=Double.parseDouble(remainTime);
      	//获取请假参数
      	String id=request.getParameter("id");
      	String department=request.getParameter("department");
      	String reason=request.getParameter("reason");
      	String ovetimeStart=request.getParameter("ovetimeStart");
      	String ovetimeOver=request.getParameter("ovetimeOver");
      	basicOffduty.setId(id);
      	basicOffduty.setDepartment(department);
      	basicOffduty.setOvetimeOver(ovetimeOver);
      	basicOffduty.setOvetimeStart(ovetimeStart);
      	basicOffduty.setReason(reason);
        //请假时长计算 格式yyyy年MM月dd日hh点
        String start= ovetimeStart;
        String over= ovetimeOver;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh点");
        long startSeconds = sdf.parse(start).getTime();//毫秒
        long overSeconds = sdf.parse(over).getTime();//毫秒
        long differ = Math.abs(overSeconds-startSeconds);
        //将时差精确到小时,并显示为 多少天（精度0.5天）
        differ=Math.round(differ/3600000);
        double days=differ/24;
        if((differ-days*24)>8){
        	days=days+1.0;
        }else if((differ-days*24)>4){
          	days=days+0.5;
        }
        //判断是否大于用户剩余可用时长
        if(days>remainTimes){
        	//返回新增页面页面，显示错误信息
        	request.setAttribute("error", "剩余时长不够，请修改调休时间 !");
        	return "/leave/basicoffduty/add";
        }
        String offdutyTime=Double.toString(days);
        basicOffduty.setOvertime(offdutyTime);
        //更新数据库
        basicOffdutyService.update(basicOffduty);
        return "redirect:/leave/basicoffduty/list";
    }

    /**
     * 查看列表（个人）
     * @param model 调休实体
     * @return 调休列表
    */
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String list(Model model,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
        Page<BasicOffduty> page_basicOffduty=new Page<BasicOffduty>();
    	page_basicOffduty.setPageSize(2);
      	Map<String, Object> map_basicOffduty=new HashMap<>();
		map_basicOffduty.put("name", basicUser.getName());
		page_basicOffduty.setParams(map_basicOffduty);
		page_basicOffduty=basicOffdutyService.selectPageList(page_basicOffduty);
		model.addAttribute("page_basicOffduty", page_basicOffduty);
		model.addAttribute("basicOffdutys", page_basicOffduty.getQueryList());
    	return "/leave/basicoffduty/list";
    }
    /**
     * 下一页 和  尾页（个人）
     * @param model 调休实体
     * @return 调休列表
     * @return
     */
    @RequestMapping(value="/listnext1", method=RequestMethod.GET)
    public String listNext1(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
        int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    int pageCount=Integer.parseInt(req.getParameter("pageCount"));
  	    Page<BasicOffduty> page_basicOffduty=new Page<BasicOffduty>();
    	page_basicOffduty.setPageSize(2);
      	if(curPage<pageCount){
      		page_basicOffduty.setCurPage(++curPage);
	    }else{
	    	page_basicOffduty.setCurPage(curPage);
	    }
    	Map<String, Object> map_basicOffduty=new HashMap<>();
		map_basicOffduty.put("name", basicUser.getName());
		page_basicOffduty.setParams(map_basicOffduty);
    	//根据所属部门多少，使用不同的分页查询
    	page_basicOffduty=basicOffdutyService.selectPageList(page_basicOffduty);
		model.addAttribute("page_basicOffduty", page_basicOffduty);
    	model.addAttribute("basicOffdutys", page_basicOffduty.getQueryList());
        return "/leave/basicoffduty/list";
    }
    /**
     * 上一页 和  首页（个人）
     * @param model 调休实体
     * @return 调休列表
     * @return
     */
    @RequestMapping(value="/listbefore1", method=RequestMethod.GET)
    public String listBefore1(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
        int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    //int pageCount=Integer.parseInt(req.getParameter("pageCount"));
  	    Page<BasicOffduty> page_basicOffduty=new Page<BasicOffduty>();
    	page_basicOffduty.setPageSize(2);
        if(curPage>1){
        	page_basicOffduty.setCurPage(--curPage);
   	    }else{
   	    	page_basicOffduty.setCurPage(curPage);
   	    }
    	Map<String, Object> map_basicOffduty=new HashMap<>();
		map_basicOffduty.put("name", basicUser.getName());
		page_basicOffduty.setParams(map_basicOffduty);
    	page_basicOffduty=basicOffdutyService.selectPageList(page_basicOffduty);
		model.addAttribute("page_basicOffduty", page_basicOffduty);
    	model.addAttribute("basicOffdutys", page_basicOffduty.getQueryList());
        return "/leave/basicoffduty/list";
    }
    
    /**
     * 查看调休详情
     * @param id 调休ID
     * @param model 调休实体
     * @return 某调休信息页面
    */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String show(@PathVariable String id, Model model){
          BasicOffduty basicOffduty = (BasicOffduty)basicOffdutyService.selectById(id);
          model.addAttribute("basicOffduty",basicOffduty);
          return "/leave/basicoffduty/show";
    }
    /**
     * 审核调休页面
     * @param id 调休ID
     * @param model 调休实体
     * @return 审核调休列表页面
    */
    @RequestMapping(value="/{id}/caudit", method=RequestMethod.GET)
    public String caudit(@PathVariable String id, Model model,HttpSession session){
    	BasicUser basicUser=basicUserService.selectById(id);
    	//根据所属部门数组
    	String[] departments=basicUser.getDepartment().split(",");
    	//查询某部门调休的记录，并按state,createTime排序
    	Page<BasicOffduty> page_basicOffduty=new Page<BasicOffduty>();
    	page_basicOffduty.setPageSize(5);
    	Map<String, Object> map_basicOffduty=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicOffduty.put("department"+i, departments[i]);
		}
		page_basicOffduty.setParams(map_basicOffduty);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicOffduty=basicOffdutyService.selectPageListMonth(page_basicOffduty);
    	}else {
    		page_basicOffduty=basicOffdutyService.selectPageList(page_basicOffduty);
		}
    	session.setAttribute("page_basicOffduty", page_basicOffduty);
    	session.setAttribute("basicOffdutys", page_basicOffduty.getQueryList());
    	model.addAttribute("page_basicOffduty", page_basicOffduty);
    	model.addAttribute("basicOffdutys", page_basicOffduty.getQueryList());
    	return "/leave/basicoffduty/caudit";
    }
    /**
     * 下一页 和  尾页(审核调休)
     * @param model 调休实体
     * @return 审核调休列表页面
     */
    @RequestMapping(value="/listnext2", method=RequestMethod.GET)
    public String listNext2(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
        //根据所属部门数组
        String[] departments=basicUser.getDepartment().split(",");
        //查询某部门加班的记录，并按state,createTime排序
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    int pageCount=Integer.parseInt(req.getParameter("pageCount"));
  	    //查询某部门调休的记录，并按state,createTime排序
    	Page<BasicOffduty> page_basicOffduty=new Page<BasicOffduty>();
    	page_basicOffduty.setPageSize(5);
      	if(curPage<pageCount){
      		page_basicOffduty.setCurPage(++curPage);
	    }else{
	    	page_basicOffduty.setCurPage(curPage);
	    }
    	Map<String, Object> map_basicOffduty=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicOffduty.put("department"+i, departments[i]);
		}
		page_basicOffduty.setParams(map_basicOffduty);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicOffduty=basicOffdutyService.selectPageListMonth(page_basicOffduty);
    	}else {
    		page_basicOffduty=basicOffdutyService.selectPageList(page_basicOffduty);
		}
    	session.setAttribute("page_basicOffduty", page_basicOffduty);
    	session.setAttribute("basicOffdutys", page_basicOffduty.getQueryList());
    	model.addAttribute("page_basicOffduty", page_basicOffduty);
    	model.addAttribute("basicOffdutys", page_basicOffduty.getQueryList());
        return "/leave/basicoffduty/caudit";
    }
    /**
     * 上一页 和  首页(审核调休)
     * @param model 调休实体
     * @return 审核调休列表页面
     */
    @RequestMapping(value="/listbefore2", method=RequestMethod.GET)
    public String listBefore2(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
        //根据所属部门数组
        String[] departments=basicUser.getDepartment().split(",");
        //查询某部门加班的记录，并按state,createTime排序
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    //int pageCount=Integer.parseInt(req.getParameter("pageCount"));
  	    //查询某部门调休的记录，并按state,createTime排序
    	Page<BasicOffduty> page_basicOffduty=new Page<BasicOffduty>();
    	page_basicOffduty.setPageSize(5);
        if(curPage>1){
        	page_basicOffduty.setCurPage(--curPage);
   	    }else{
   	    	page_basicOffduty.setCurPage(curPage);
   	    }
    	Map<String, Object> map_basicOffduty=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicOffduty.put("department"+i, departments[i]);
		}
		page_basicOffduty.setParams(map_basicOffduty);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicOffduty=basicOffdutyService.selectPageListMonth(page_basicOffduty);
    	}else {
    		page_basicOffduty=basicOffdutyService.selectPageList(page_basicOffduty);
		}
    	session.setAttribute("page_basicOffduty", page_basicOffduty);
    	session.setAttribute("basicOffdutys", page_basicOffduty.getQueryList());
    	model.addAttribute("page_basicOffduty", page_basicOffduty);
    	model.addAttribute("basicOffdutys", page_basicOffduty.getQueryList());
        return "/leave/basicoffduty/caudit";
    }
    
    /**
     * 审核 某一个调休单
     * @param id 调休ID
     * @param model 调休实体
     * @return 审核某个调休记录页面
    */
    @RequestMapping(value="/{id}/check", method=RequestMethod.GET)
    public String check(@PathVariable String id, Model model,HttpSession session){
    	BasicOffduty basicOffduty=basicOffdutyService.selectById(id);
    	session.setAttribute("basicOffduty", basicOffduty);
        model.addAttribute("basicOffduty",basicOffduty);
        return "/leave/basicoffduty/check";
    }
    /**
     * 审核 某一个调休单 (该方法要想数据库中减少用户剩余时长)
     * @param id 调休ID
     * @param model 调休实体
     * @return 审核调休列表页面
    */
    @RequestMapping(value="/{id}/check", method=RequestMethod.POST)
    public String check(@PathVariable String id,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
    	//判断是否同意
    	//查询该记录用户id_basicUser剩余可调节时间
    	String name=request.getParameter("name");
    	BasicUser id_basicUser=new BasicUser();
    	id_basicUser=basicUserService.selectByName(name);
    	String state=request.getParameter("state");
    	String overtime=request.getParameter("overtime");
    	BasicOffduty basicOffduty=new BasicOffduty();
    	BasicUser basicUser=(BasicUser) session.getAttribute("basicUser");
    	basicOffduty.setId(id);
    	if(state.equals("0")){
    		//一票否决权
    		basicOffduty.setState("0");
        	basicOffdutyService.update(basicOffduty);
    	}else {
    		//判断用户是组长，还是经理
        	String position=basicUser.getPosition();
        	if(position.equals("组长")){
        		basicOffduty.setState("2");
        		basicOffdutyService.update(basicOffduty);
        	}else if(position.equals("部门经理")){
        		basicOffduty.setState("3");
        		basicOffdutyService.update(basicOffduty);
            	//向用户表中减少用户剩余时长
            	String ageTime=id_basicUser.getRemainTime();
            	double ageTime_1=Double.parseDouble(ageTime);
            	double overtime_1=Double.parseDouble(overtime);
            	if(ageTime_1<overtime_1){
            		//调休时间过长
            		session.setAttribute("error_offduty", "该员工剩余可调休时间不足");
            		return "/leave/basicoffduty/check";
            	}
            	double sumTime=ageTime_1-overtime_1;
            	String sumTime_1=Double.toString(sumTime);
            	id_basicUser.setRemainTime(sumTime_1);
            	basicUserService.update(id_basicUser);
        	}
		}
        return "redirect:/leave/basicoffduty/"+basicUser.getId()+"/caudit";
    }
    /**
     * 下一页 和  尾页(调休月报审核)
     * @param model 调休实体
     * @return 调休月报列表页面
     */
    @RequestMapping(value="/listnext", method=RequestMethod.GET)
    public String listNext_BasicLeave(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
        //根据所属部门数组
        String[] departments=basicUser.getDepartment().split(",");
        //查询某部门加班的记录，并按state,createTime排序
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    int pageCount=Integer.parseInt(req.getParameter("pageCount"));
  	    //查询某部门调休的记录，并按state,createTime排序
    	Page<BasicOffduty> page_basicOffduty=new Page<BasicOffduty>();
    	page_basicOffduty.setPageSize(2);
      	if(curPage<pageCount){
      		page_basicOffduty.setCurPage(++curPage);
	    }else{
	    	page_basicOffduty.setCurPage(curPage);
	    }
    	Map<String, Object> map_basicOffduty=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicOffduty.put("department"+i, departments[i]);
		}
		map_basicOffduty.put("state", "3");
		page_basicOffduty.setParams(map_basicOffduty);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicOffduty=basicOffdutyService.selectPageListMonth(page_basicOffduty);
    	}else {
    		page_basicOffduty=basicOffdutyService.selectPageList(page_basicOffduty);
		}
    	session.setAttribute("page_basicOffduty", page_basicOffduty);
    	session.setAttribute("basicOffdutys", page_basicOffduty.getQueryList());
    	model.addAttribute("page_basicOffduty", page_basicOffduty);
    	model.addAttribute("basicOffdutys", page_basicOffduty.getQueryList());
        return "/leave/basicmonthly/caudit_offduty";
    }
    /**
     * 上一页 和  首页(调休月报审核)
     * @param model 调休实体
     * @return 调休月报列表页面
     */
    @RequestMapping(value="/listbefore", method=RequestMethod.GET)
    public String listBefore(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
        //根据所属部门数组
        String[] departments=basicUser.getDepartment().split(",");
        //查询某部门加班的记录，并按state,createTime排序
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    //int pageCount=Integer.parseInt(req.getParameter("pageCount"));
  	    //查询某部门调休的记录，并按state,createTime排序
    	Page<BasicOffduty> page_basicOffduty=new Page<BasicOffduty>();
    	page_basicOffduty.setPageSize(2);
        if(curPage>1){
        	page_basicOffduty.setCurPage(--curPage);
   	    }else{
   	    	page_basicOffduty.setCurPage(curPage);
   	    }
    	Map<String, Object> map_basicOffduty=new HashMap<>();
		for(int i=0;i<departments.length;i++){
			map_basicOffduty.put("department"+i, departments[i]);
		}
		map_basicOffduty.put("state", "3");
		page_basicOffduty.setParams(map_basicOffduty);
    	//根据所属部门多少，使用不同的分页查询
    	if(departments.length>1){
    		page_basicOffduty=basicOffdutyService.selectPageListMonth(page_basicOffduty);
    	}else {
    		page_basicOffduty=basicOffdutyService.selectPageList(page_basicOffduty);
		}
    	session.setAttribute("page_basicOffduty", page_basicOffduty);
    	session.setAttribute("basicOffdutys", page_basicOffduty.getQueryList());
    	model.addAttribute("page_basicOffduty", page_basicOffduty);
    	model.addAttribute("basicOffdutys", page_basicOffduty.getQueryList());
        return "/leave/basicmonthly/caudit_offduty";
    }
    /**
     * 关键字搜索
     * @param model 调休实体
     * @return 调休月报列表页面
     */
    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String search(Model model,HttpServletRequest req,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	List<BasicOffduty> list=new ArrayList<>();
    	BasicOffduty basicOffduty=new BasicOffduty();
    	basicOffduty.setState("3");
    	//根据所属部门数组
    	String[] departments=basicUser.getDepartment().split(",");
    	//查询某部门请假的记录，并按state,createTime排序
    	Page<BasicOffduty> page_basicOffduty=new Page<BasicOffduty>();
    	//获取要查询的内容
    	String keyword=req.getParameter("keyword");
    	String year=req.getParameter("year");
    	String month=req.getParameter("month");
    	if(keyword.equals("")){
    		//只查年月
			String time="%"+year+"年"+month+"月%";
			basicOffduty.setOvetimeStart(time);
    	}else if(year.equals("")||month.equals("")){
			//只查关键字(只能是员工姓名)
    		basicOffduty.setName("%"+keyword+"%");
		}else {
			String time="%"+year+"年"+month+"月%";
    		basicOffduty.setName("%"+keyword+"%");
			basicOffduty.setOvetimeStart(time);
		}
		for(int i=0;i<departments.length;i++){
			basicOffduty.setDepartment(departments[i]);
			list.addAll(basicOffdutyService.selectList(basicOffduty));
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
    	model.addAttribute("page_basicOffduty", page_basicOffduty);
    	model.addAttribute("basicOffdutys", page_basicOffduty.getQueryList());
        return "/leave/basicmonthly/caudit_offduty";
    }
}