/*
 * BasicMonthlyController.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-17 Created
 */
package com.hesc.leave.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hesc.leave.pojo.BasicLeave;
import com.hesc.leave.pojo.BasicMonthly;
import com.hesc.leave.pojo.BasicOffduty;
import com.hesc.leave.pojo.BasicOvertime;
import com.hesc.leave.pojo.BasicUser;
import com.hesc.leave.service.BasicLeaveService;
import com.hesc.leave.service.BasicMonthlyService;
import com.hesc.leave.service.BasicOffdutyService;
import com.hesc.leave.service.BasicOvertimeService;
import com.hesc.leave.service.BasicUserService;
import com.hesc.trundle.db.page.Page;

@Controller
@RequestMapping("/leave/basicmonthly")
public class BasicMonthlyController {
    //上传图片类型
    private String[] fileType = new String[]{".xls",".xlsx",".xlt",".xlsm"};
    @Autowired
    private BasicMonthlyService basicMonthlyService;
    @Resource(name="basicUserService")
    private BasicUserService basicUserService;
    @Resource(name="basicOffdutyService")
    private BasicOffdutyService basicOffdutyService;
    @Resource(name="basicLeaveService")
    private BasicLeaveService basicLeaveService;
    @Resource(name="basicOvertimeService")
    private BasicOvertimeService basicOvertimeService;
    /**
     * 新增月报
     * @param model 月报实体
     * @return 月报新增页面
    */
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String add(Model model){
          model.addAttribute("basicMonthly", new BasicMonthly());
          return "/leave/basicmonthly/add";
    }
    /**
     * 新增月报
     * @param model 月报实体
     * @return 月报列白哦页面
    */
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String add(@Valid BasicMonthly basicMonthly, BindingResult result,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    	if(result.hasErrors()){
            return "/leave/basicmonthly/add";
        }
        response.setContentType("text/html");     
        response.setCharacterEncoding("UTF-8");
        //获取请假参数
      	String name=request.getParameter("name");
      	String department=request.getParameter("department");
      	String month=request.getParameter("month");
      	//String url=request.getParameter("url");
      	basicMonthly.setName(name);
      	basicMonthly.setDepartment(department);
      	basicMonthly.setMonth(month);
        //处理上传文件
        try {  
        	//判断路径是否存在，不存在则创建  
        	String uploadPath = request.getSession().getServletContext().getRealPath("/")+"images/load/";
        	String tempPath=request.getSession().getServletContext().getRealPath("/")+"images/temp/";
        	//备份文件地址
        	String basedir="D:/Workspace/huawangxin_leave/src/main/webapp/images/load/";
        	if(!new File(uploadPath).isDirectory()){
        		new File(uploadPath).mkdirs();
        	}
        	if(!new File(tempPath).isDirectory()){
        		new File(tempPath).mkdirs();
        	}
        DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10*1024*1024); //最大缓存
		factory.setRepository(new File(tempPath));//临时文件目录
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(20*1024*1024);//文件最大上限
		
		String filePath = null;
		try {
			List<FileItem> items = upload.parseRequest(request);//获取所有文件列表
			for (FileItem item : items) {
				//获得文件名，这个文件名包括路径
				if(!item.isFormField()){
					//文件名
					String fileName = item.getName().toLowerCase();
					
					if(fileName.endsWith(fileType[0])||fileName.endsWith(fileType[1])||fileName.endsWith(fileType[2])||fileName.endsWith(fileType[3])){
						String uuid = UUID.randomUUID().toString();
						filePath = uploadPath+uuid+fileName.substring(fileName.lastIndexOf("."));
						item.write(new File(filePath));
						String urls=uuid+fileName.substring(fileName.lastIndexOf("."));//文件名加后缀
						//workspace中备份
						basedir=basedir+uuid+fileName.substring(fileName.lastIndexOf("."));
						item.write(new File(basedir));
						System.out.println("urls:-------------"+urls);
						basicMonthly.setUrl(urls);
					}
				}else{// 表单  
                    String fieldName = item.getFieldName();  
                    if ("name".equals(fieldName)) {  
                    	name = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
                    }else if ("department".equals(fieldName)) {  
                    	department = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"); 
                    }else if ("month".equals(fieldName)) {  
                    	month =new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");  
                    } 
					//获取请假参数
			      	basicMonthly.setName(name);
			      	basicMonthly.setDepartment(department);
			      	basicMonthly.setMonth(month);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}}catch(Exception ee) { 
            ee.printStackTrace();  
        } 
      	
       //插如数据库
       basicMonthlyService.insert(basicMonthly);
       return "redirect:/leave/basicmonthly/list";
    }
    /**
     * 删除月报
     * @param id 月报ID
     * @return 月报列表
    */
    @RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
    public String delete(@PathVariable String id){
          basicMonthlyService.deleteById(id);
          return "redirect:/leave/basicmonthly/list";
    }

    /**
     * 修改月报
     * @param id 月报ID
     * @param model 月报实体
     * @return 月报修改页面
    */
    @RequestMapping(value="/{id}/update", method=RequestMethod.GET)
    public String update(@PathVariable String id, Model model,HttpSession session){
    	BasicMonthly basicMonthly=basicMonthlyService.selectById(id);
    	session.setAttribute("basicMonthly", basicMonthly);
        model.addAttribute("basicMonthly",basicMonthly);
        return "/leave/basicmonthly/update";
    }
    /**
     * 修改月报
     * @param model 月报实体
     * @return 月报列表页面
    */
    @RequestMapping(value="/{id}/update", method=RequestMethod.POST)
    public String update(@Valid BasicMonthly basicMonthly, BindingResult result,HttpServletRequest request,HttpServletResponse response,HttpSession session){
          if(result.hasErrors()){
                return "/leave/basicmonthly/update";
          }
          response.setContentType("text/html");     
          response.setCharacterEncoding("UTF-8");
          System.out.println("11");
          //处理上传文件
          try {  
          	//判断路径是否存在，不存在则创建  
          	String uploadPath = request.getSession().getServletContext().getRealPath("/")+"images/load/";
          	String tempPath=request.getSession().getServletContext().getRealPath("/")+"images/temp/";
          	//备份文件地址
          	String basedir="D:/Workspace/huawangxin_leave/src/main/webapp/images/load/";
          	if(!new File(uploadPath).isDirectory()){
          		new File(uploadPath).mkdirs();
          	}
          	if(!new File(tempPath).isDirectory()){
          		new File(tempPath).mkdirs();
          	}
          DiskFileItemFactory factory = new DiskFileItemFactory();
  		factory.setSizeThreshold(10*1024*1024); //最大缓存
  		factory.setRepository(new File(tempPath));//临时文件目录
  		
  		ServletFileUpload upload = new ServletFileUpload(factory);
  		upload.setSizeMax(20*1024*1024);//文件最大上限
  		
  		String filePath = null;
  		String url1=null;
  		String url2=null;
  		try {
  			List<FileItem> items = upload.parseRequest(request);//获取所有文件列表
  			for (FileItem item : items) {
  				//获得文件名，这个文件名包括路径
  				if(!item.isFormField()){
  					//文件名
  					String fileName = item.getName().toLowerCase();
  					
  					if(fileName.endsWith(fileType[0])||fileName.endsWith(fileType[1])||fileName.endsWith(fileType[2])||fileName.endsWith(fileType[3])){
  						String uuid = UUID.randomUUID().toString();
  						filePath = uploadPath+uuid+fileName.substring(fileName.lastIndexOf("."));
  						item.write(new File(filePath));
  						String urls=uuid+fileName.substring(fileName.lastIndexOf("."));//文件名加后缀
  						//workspace中备份
  						basedir=basedir+uuid+fileName.substring(fileName.lastIndexOf("."));
  						item.write(new File(basedir));
  						System.out.println("urls:-------------"+urls);
  						url1=urls;
  						//basicMonthly.setUrl(urls);
  					}
  				}else{
  					// 表单   获取请假参数
  					String id=null;
  		        	String department=null;
  		        	String month=null;
  		        	String name=null;
  					String fieldName = item.getFieldName();  
                      if ("id".equals(fieldName)) {  
                    	  id = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");  
        			      basicMonthly.setId(id);
                      }else if ("department".equals(fieldName)) {  
                      	department = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
      			      	basicMonthly.setDepartment(department); 
                      }else if ("month".equals(fieldName)) {  
                      	month =new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"); 
      			      	basicMonthly.setMonth(month);
                      }else if ("url".equals(fieldName)) {
                    	url2 =new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
                      }else if ("name".equals(fieldName)) {
                    	name =new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
                        basicMonthly.setName(name); 
                      }
  					
  				}
            	//获取请假参数
                if(url1!=""&&url1!=null){
                    basicMonthly.setUrl(url1);
                }else {
      				basicMonthly.setUrl(url2);
				}
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		}}catch(Exception ee) { 
              ee.printStackTrace();  
          } 
        	basicMonthly.getMonth();
         //插如数据库
         basicMonthlyService.update(basicMonthly);
         return "redirect:/leave/basicmonthly/list";
    }

    /**
     * 查看月报列表
     * @param model 月报实体
     * @return 月报列表页面
    */
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String list(Model model,HttpSession session){
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	List<BasicMonthly> basicMonthlylist=new ArrayList<BasicMonthly>();
    	basicMonthlylist=basicMonthlyService.selectByName(basicUser.getName());
        model.addAttribute("list", basicMonthlylist);
        return "/leave/basicmonthly/list";
    }

    /**
     * 查看月报详情
     * @param id 月报ID
     * @param model 月报实体
     * @return 某月报信息页面
    */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String show(@PathVariable String id, Model model){
          BasicMonthly basicMonthly = (BasicMonthly)basicMonthlyService.selectById(id);
          model.addAttribute("basicMonthly",basicMonthly);
          return "/leave/basicmonthly/show";
    }
    
    /**
     * 审核月报页面(加班 调休 请假)
     * @param id 月报ID
     * @param model 月报实体
     * @return 三个信息实体的月报列表
    */
    @RequestMapping(value="/{id}/caudit", method=RequestMethod.GET)
    public String caudit(@PathVariable String id, Model model,HttpSession session){
    	BasicUser basicUser=basicUserService.selectById(id);
    	//根据所属部门数组
    	String[] departments=basicUser.getDepartment().split(",");
    	/********************************************/
    	//查询某部门请假的记录，并按state,createTime排序
    	Page<BasicLeave> page_basicleave=new Page<BasicLeave>();
    	page_basicleave.setPageSize(2);
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
    	/********************************************/
    	//查询某部门加班的记录，并按state,createTime排序
    	Page<BasicOvertime> page_basicOvertime=new Page<BasicOvertime>();
    	page_basicOvertime.setPageSize(2);
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
    	/********************************************/
    	//查询某部门调休的记录，并按state,createTime排序
    	Page<BasicOffduty> page_basicOffduty=new Page<BasicOffduty>();
    	page_basicOffduty.setPageSize(2);
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
    	/********************************************/
        return "/leave/basicmonthly/caudit_leave";
    }
    /**
     * 生成月报
     * @param id 月报ID
     * @param model 月报实体
     * @return 月报列表页面
    */
    @RequestMapping(value="/toMonth", method=RequestMethod.POST)
    public String toMonth(Model model,HttpSession session,HttpServletRequest request){
    	//获取月报时间和月报类型
    	String year=request.getParameter("year");
    	String month=request.getParameter("month");
    	String type=request.getParameter("type");
    	String monthTime=year+"年"+month+"月";
    	BasicUser basicUser=(BasicUser)session.getAttribute("basicUser");
    	//根据所属部门数组
    	String[] departments=basicUser.getDepartment().split(",");
    	List<BasicLeave> list_leave=new ArrayList<BasicLeave>();
    	List<BasicOvertime> list_overtime=new ArrayList<BasicOvertime>();
    	List<BasicOffduty> list_offduty=new ArrayList<BasicOffduty>();
    	List<Object> list=new ArrayList<>();
    	//判断要搜索的范围
    	if(type.equals("1")){
    		//请假(条件：state=3 部门=* startTime=*)
    		//查询某部门请假的记录，并按state,createTime排序
    		for(int i=0;i<departments.length;i++){
    			BasicLeave leave=new BasicLeave();
    			leave.setDepartment(departments[i]);
    			leave.setState("3");
    			leave.setLeaveStart(monthTime);
    			List<BasicLeave> list_leave1=basicLeaveService.selectListMonth(leave);
    			list_leave.addAll(list_leave1);
    			list.addAll(list_leave1);
    		}
    		session.setAttribute("basicLeaves", list_leave);
            model.addAttribute("basicLeaves",list_leave);
            return "/leave/basicmonthly/caudit";
    	}else if(type.equals("2")){
    		//加班
    		//查询某部门加班的记录，并按state,createTime排序
    		for(int i=0;i<departments.length;i++){
    			BasicOvertime overtime=new BasicOvertime();
    			overtime.setDepartment(departments[i]);
    			overtime.setState("3");
    			overtime.setOvetimeStart(monthTime);
    			list_overtime.addAll(basicOvertimeService.selectListMonth(overtime));
    		}
    		session.setAttribute("basicOvertimes", list_overtime);
    		model.addAttribute("basicOvertimes",list_overtime);
            return "/leave/basicmonthly/caudit";
    	}else if(type.equals("3")){
    		//调休
    		//查询某部门调休的记录，并按state,createTime排序
    		for(int i=0;i<departments.length;i++){
    			BasicOffduty basicOffduty=new BasicOffduty();
    			basicOffduty.setDepartment(departments[i]);
    			list_offduty.addAll(basicOffdutyService.selectListMonth(basicOffduty));
    		}
    		session.setAttribute("basicOffdutys", list_offduty);
          	model.addAttribute("basicOffdutys",list_offduty);
          	return "/leave/basicmonthly/caudit";
    	}else if(type.equals("0")){
    		//全部
    		//查询某部门请假的记录，并按state,createTime排序
    		for(int i=0;i<departments.length;i++){
    			BasicLeave leave=new BasicLeave();
    			leave.setDepartment(departments[i]);
    			leave.setState("3");
    			leave.setLeaveStart(monthTime);
    			List<BasicLeave> list_leave1=basicLeaveService.selectListMonth(leave);
    			list.addAll(list_leave1);
    		}
    		//查询某部门加班的记录，并按state,createTime排序
    		for(int i=0;i<departments.length;i++){
    			BasicOvertime overtime=new BasicOvertime();
    			overtime.setDepartment(departments[i]);
    			overtime.setState("3");
    			overtime.setOvetimeStart(monthTime);
    			list.addAll(basicOvertimeService.selectListMonth(overtime));
    		}
    		//查询某部门调休的记录，并按state,createTime排序
    		for(int i=0;i<departments.length;i++){
    			BasicOffduty basicOffduty=new BasicOffduty();
    			basicOffduty.setDepartment(departments[i]);
    			list.addAll(basicOffdutyService.selectListMonth(basicOffduty));
    		}
    		session.setAttribute("lists", list);
          	model.addAttribute("lists",list);
          	return "/leave/basicmonthly/caudit";
    	}
        return "/leave/basicmonthly/caudit";
    }
}