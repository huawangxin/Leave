/*
 * BasicUserController.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hesc.leave.pojo.BasicUser;
import com.hesc.leave.service.BasicUserService;
import com.hesc.trundle.db.page.Page;

@Controller
@RequestMapping("/leave/basicuser")
public class BasicUserController {

    @Autowired
    private BasicUserService basicUserService;
    /**
	 * @param 登录
	 * @param model 用户实体类
	 * @return 返回用户登录页面
	 */
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(Model model,HttpSession session){
    	model.addAttribute("basicUser", new BasicUser());
        return "/leave/basicuser/login";
    }
    /**
	 * @param 登录
     * @param name 用户名
     * @param password 密码
	 * @param model 用户
     * @throws Exception
	 * @return true 返回用户登录页面 || false 登录界面
	 */
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@Valid String name,String password,Model model,HttpSession session,HttpServletRequest req) throws Exception{
    	BasicUser basicUser=new BasicUser();
    	basicUser=basicUserService.selectByName(name);
    	if(basicUser!=null){
    		//判断是否密码一致
    		if(password.equals(basicUser.getPassword())){
    			model.addAttribute("basicUser", basicUser);
    			session.setAttribute("basicUser", basicUser);
    			//获取当前系统的时间
    			Date date=new Date();
    			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    			String now=dateFormat.format(date);
    			session.setAttribute("now", now);
    			model.addAttribute("now", now);
    			return "redirect:/leave/basicuser/main";
    		}
    	}
    	req.setAttribute("error", "用户名或密码错误 !");
    	model.addAttribute("basicUser", new BasicUser());
    	return "/leave/basicuser/login";
    }
    /**
	 * @param (主界面)父级页面
	 * @param model 用户名
	 * @return 返回用户登录页面
	 */
    @RequestMapping(value="/main", method=RequestMethod.GET)
    public String main(Model model,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	//判断用户是否为空，null则返回登录界面
    	if(basicUser==null){
    		return "redirect:/leave/basicuser/login";
    	}
		session.setAttribute("basicUser", basicUser);
    	model.addAttribute("basicUser", basicUser);
        return "/leave/basicuser/main";
    }
    /**
	 * @param 顶部页面
	 * @param model 用户名
	 * @return 返回用户登录页面
	 */
    @RequestMapping(value="/top", method=RequestMethod.GET)
    public String top(Model model,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
		model.addAttribute("basicUser", basicUser);
        return "/leave/basicuser/top";
    }
    /**
	 * @param 左侧页面
	 * @param model 用户名
	 * @return 返回用户登录页面
	 */
    @RequestMapping(value="/left", method=RequestMethod.GET)
    public String left(Model model,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
		model.addAttribute("basicUser", basicUser);
        return "/leave/basicuser/left";
    }
    /**
	 * @param 右侧主页面
	 * @param model 用户名
	 * @return 返回用户登录页面
	 */
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String index(Model model,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
		model.addAttribute("basicUser", basicUser);
        return "/leave/basicuser/index";
    }
    /**
     * 新增员工
     * @param model
     * @return 新增员工界面
     */
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String add(Model model){
    	model.addAttribute("basicUser_new", new BasicUser());
        return "/leave/basicuser/add";
    }
    /**
     * 新增员工
     * @param BasicUser 员工实体
     * @return true 跳转到员工主界面 || false 返回到新增员工界面
     */
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String add(@Valid BasicUser basicUser_new, BindingResult result,HttpSession session){
          if(result.hasErrors()){
                return "/leave/basicuser/add";
          }
          basicUserService.insert(basicUser_new);
          BasicUser basicUser=(BasicUser) session.getAttribute("basicUser");
          return "redirect:/leave/basicuser/"+basicUser.getId()+"/caudit";
    }

    /**
     * 删除
     * @param id 员工ID
     * @return 返回员工列表
    */
    @RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
    public String delete(@PathVariable String id){
          basicUserService.deleteById(id);
          return "redirect:/leave/basicuser/list";
    }

    /**
     * 修改 个人信息
     * @param id 员工ID
     * @param model
     * @return 将员工信息返回到修改员工信息界面
    */
    @RequestMapping(value="/{id}/update", method=RequestMethod.GET)
    public String update(@PathVariable String id, Model model){
    	model.addAttribute("basicUser", basicUserService.selectById(id));
        return "/leave/basicuser/update";
    }
    /**
     * 修改 个人信息
     * @param BasicUser 员工信息
     * @param model
     * @return 修改员工信息界面
    */
    @RequestMapping(value="/{id}/update", method=RequestMethod.POST)
    public String update(@Valid BasicUser basicUser, BindingResult result,HttpSession session){
          if(result.hasErrors()){
                return "/leave/basicuser/update";
          }
          basicUserService.update(basicUser);
          session.setAttribute("basicUser", basicUser);
          return "redirect:/leave/basicuser/"+basicUser.getId()+"/update";
    }
    /**
     * 修改 下级员工信息
     * @param id 员工id
     * @param model 员工
     * @return 修改 下级员工信息界面
    */
    @RequestMapping(value="/{id}/update_all", method=RequestMethod.GET)
    public String update_all(@PathVariable String id, Model model){
    	model.addAttribute("basicUser_all", basicUserService.selectById(id));
        return "/leave/basicuser/update_all";
    }
    /**
     * 修改 下级员工信息
     * @param id 员工id
     * @param model 员工
     * @return 修改 下级员工信息列表
    */
    @RequestMapping(value="/{id}/update_all", method=RequestMethod.POST)
    public String update_all(@Valid BasicUser basicUser_all, BindingResult result,HttpSession session){
          if(result.hasErrors()){
                return "/leave/basicuser/update_all";
          }
          basicUserService.update(basicUser_all);
          return "redirect:/leave/basicuser/list";
    }
    /**
     * 查看列表(适用于本小组)
     * @param model 员工
     * @return  本小组的员工列表
    */
    @RequestMapping(value="/list_only", method=RequestMethod.GET)
    public String list_only(Model model,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	BasicUser basicUser1=new BasicUser();
    	List<BasicUser> list=new ArrayList<>();
    	Page<BasicUser> page_basicuser=new Page<BasicUser>();
    	//根据所属部门数组
      	String[] departments=basicUser.getDepartment().split(",");
      	for(int i=0;i<departments.length;i++){
      		basicUser1.setDepartment("%"+departments[i]+"%");
      		list.addAll(basicUserService.selectList(basicUser1));
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
      	model.addAttribute("page_basicuser", page_basicuser);
    	model.addAttribute("basicUsers", list);
        return "/leave/basicuser/list";
    }
    
    /**
     * 查看列表(适用于所有人)
     * @param model 员工
     * @return 所有员工列表
    */
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String list(Model model,HttpSession session){
    	Page<BasicUser> page_basicuser=new Page<BasicUser>();
      	page_basicuser.setPageSize(5);
      	page_basicuser=basicUserService.selectPageList(page_basicuser);
      	model.addAttribute("page_basicuser", page_basicuser);
    	model.addAttribute("basicUsers", page_basicuser.getQueryList());
        return "/leave/basicuser/list_all";
    }
    
    /**
     * 下一页 和  尾页(审核下属列表)
     * @param model 用户列表
     * @param curPage 当前页
     * @param pageCount 总页数
     * @return 用户列表
     */
    @RequestMapping(value="/listnext1", method=RequestMethod.GET)
    public String listNext1(Model model,HttpServletRequest req,HttpSession session){
    	int curPage=Integer.parseInt(req.getParameter("curPage"));
	    int pageCount=Integer.parseInt(req.getParameter("pageCount"));
      	Page<BasicUser> page_basicuser=new Page<BasicUser>();
      	page_basicuser.setPageSize(5);
      	if(curPage<pageCount){
      		page_basicuser.setCurPage(++curPage);
	    }else{
	    	page_basicuser.setCurPage(curPage);
	    }
  		page_basicuser=basicUserService.selectPageList(page_basicuser);
      	model.addAttribute("page_basicuser", page_basicuser);
      	model.addAttribute("basicUsers", page_basicuser.getQueryList());
      	return "/leave/basicuser/list_all";
    }
    /**
     * 上一页 和  首页pageCount
     * @param model  用户列表
     * @param curPage 当前页
     * @param pageCount 总页数
     * @return 用户列表
     */
    @RequestMapping(value="/listbefore1", method=RequestMethod.GET)
    public String listBefore1(Model model,HttpServletRequest req,HttpSession session){
        int curPage=Integer.parseInt(req.getParameter("curPage"));
  	    //int pageCount=Integer.parseInt(req.getParameter("pageCount"));
        Page<BasicUser> page_basicuser=new Page<BasicUser>();
        page_basicuser.setPageSize(5);
        if(curPage>1){
        	page_basicuser.setCurPage(--curPage);
   	    }else{
   	    	page_basicuser.setCurPage(curPage);
   	    }
        page_basicuser=basicUserService.selectPageList(page_basicuser);
        model.addAttribute("page_basicuser", page_basicuser);
        model.addAttribute("basicUsers", page_basicuser.getQueryList());
        return "/leave/basicuser/list_all";
    }

    /**
     * 查看某用户详情
     * @param id 用户ID
     * @param model 用户实体
     * @return 用户详情
    */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String show(@PathVariable String id, Model model){
          BasicUser basicUser = (BasicUser)basicUserService.selectById(id);
          model.addAttribute("basicUser_look",basicUser);
          return "/leave/basicuser/show";
    }
    
    /**
     * 审核 
     * @param id 用户ID
     * @param model
     * @return true 审核主界面|| false用户信息界面
    */
    @RequestMapping(value="/{id}/caudit", method=RequestMethod.GET)
    public String caudit(@PathVariable String id, Model model){
          BasicUser basicUser = (BasicUser)basicUserService.selectById(id);
          model.addAttribute("basicUser",basicUser);
          if(basicUser.getPosition().equals("组长")){
        	  return "/leave/basicuser/headcaudit";
          }else if(basicUser.getPosition().equals("部门经理")){
        	  return "/leave/basicuser/headcaudit"; 
          }
          return "/leave/basicuser/show";
    }

}