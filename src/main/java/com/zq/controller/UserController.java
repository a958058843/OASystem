package com.zq.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.zq.common.BaseController;
import com.zq.common.mail.SendEmail;
import com.zq.pojo.User;
import com.zq.service.UserService;
import com.zq.utils.SecurityMd5;

@Controller
@RequestMapping("/user")
@SuppressWarnings("all")
public class UserController extends BaseController {
	// 注入service
	@Resource
	private UserService userService;

	@RequestMapping("/userMana")
	public String userMana() {// userMana.ftl
		System.out.println("userMana");
		return "user/userMana";
	}

	@RequestMapping("/userList")
	public ModelAndView userList(HttpServletRequest request, HttpServletResponse response) {
		// 1，得到页面用于分页查询的参数
		Map<String, Object> paramMap = super.getParam(request);
		String startIndex = (String) paramMap.get("startIndex");
		String pageSize = (String) paramMap.get("pageSize");
		paramMap.put("startIndex", Integer.parseInt(startIndex));
		paramMap.put("pageSize", Integer.parseInt(pageSize));
		// 2.调用service的相关方法,获取用户列表
		Map<String, Object> resultMap = userService.findPageUser(paramMap);
		List<User> userList = (List<User>) resultMap.get("userList");
		long total = (Long) resultMap.get("total");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("total", total);
		modelAndView.setViewName("user/userList");
		Gson gson = new Gson();
		String json = gson.toJson(userList);
		return modelAndView;
	}

	@RequestMapping("/getNumberList")
	public ModelAndView getNumberList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("user/userPageNumber");
		Map<String, Object> paramMap = super.getParam(request);
		int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
		int total = Integer.parseInt(paramMap.get("total").toString());
		int startIndex = Integer.parseInt(paramMap.get("startIndex") + "");
		System.out.println("pageSize" + pageSize + "--startIndex" + startIndex + "--total" + total);
		modelAndView = super.getPageNumberInfo(total, startIndex, pageSize, modelAndView);
		return modelAndView;
	}

	@RequestMapping("/sendMail")
	public void sendMail(HttpServletRequest request, HttpServletResponse response) {
		String receiverAddress = request.getParameter("receiverAddress");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(receiverAddress + "--" + title + "--" + content);
		// 调用发送邮件的方法
		SendEmail sendMail = new SendEmail();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sendMail.SendEmailInfoUser163(receiverAddress, title, content);
			map.put("isSuccess", true);// 发送成功
		} catch (MessagingException e) {
			e.printStackTrace();
			map.put("isSuccess", false);
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		System.out.println(json);
		super.flushResponse(response, json);
	}

	/**
	 * 新增一个用户
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/add")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = super.getParam(request);
		String birthday = (String) map.get("userBirthday");
		SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = simpleDataFormat.parse(birthday);
			map.put("userBirthday", date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//密码md5处理
		String userPassword = (String) map.get("userPassword");
		userPassword = SecurityMd5.md5(userPassword);
		map.put("userPassword", userPassword);
		int i = userService.insert(map);
		Map<String, String> map1 = new HashMap<String, String>();
		if (i > 0) {
			map1.put("isSuccess", "true");
		} else {
			map1.put("isSuccess", "false");
		}
		super.flushResponse(response, new Gson().toJson(map1));
	}
	@RequestMapping("/toUpdate")
	public void toUpdate(HttpServletRequest request,HttpServletResponse response){
		String userId=request.getParameter("userId");
		User user = userService.selectByPrimaryKey(Long.parseLong(userId));
		super.flushResponse(response, new Gson().toJson(user));
	}
	@RequestMapping("/delete")
	public void delete(Long userId,HttpServletRequest request,HttpServletResponse response){
		int i=userService.deleteByPrimaryKey(userId);
		Map<String,String> map=new HashMap<String, String>();
		if (i > 0) {
			map.put("isSuccess", "true");
		} else {
			map.put("isSuccess", "false");
		}
		super.flushResponse(response, new Gson().toJson(map));
	}
	@RequestMapping("/toPieChart")
	public String toPieChart(){
		return "/echarts/pieChart";
	}
	@RequestMapping("/toBarChart")
	public String toBarChart(){
		return "/echarts/barChart";
	}
	@RequestMapping("/selectPieProvice")
	public void selectProvinceInfo(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		 try {
			resultMap =  userService.selectProvinceInfo();
			resultMap.put("isSuccess", true);
		} catch (Exception e) {
			resultMap.put("isSuccess", false);
		}
		super.flushResponse(response, new Gson().toJson(resultMap));//jackson

	}
	@RequestMapping("/selectBarProvice")
	public void selectBarProvice(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap=userService.selectBarProviceSexInfo();
			resultMap.put("isSuccess", true);
		} catch (Exception e) {
			resultMap.put("isSuccess", false);
		}
		super.flushResponse(response, new Gson().toJson(resultMap));//jackson
	}
}
