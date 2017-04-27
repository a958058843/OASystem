package com.zq.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {
	/**
	 * 把浏览器参数转化到Map集合中
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> getParam(HttpServletRequest request) {
		 Map<String, Object> paramMap = new HashMap<String, Object>();
	        String method = request.getMethod();
	        //request.getParameter("username");
	        //得到页面的所有name属性的值
	        Enumeration<?> keys = request.getParameterNames();
	        while (keys.hasMoreElements()) {//遍历
	            Object key = keys.nextElement();//得到每一个name属性
	            if(key!=null){
	            	if (key instanceof String) {
	            		//根据name属性得到传到后台的值
	            	    String value = request.getParameter(key.toString());
	            	    //tomcat7 及以下
//	            	    if("GET".equals(method)){//前台encodeURIComponent('我们');转码后到后台还是ISO-8859-1，所以还需要转码
//	            	         try {
//								value =new String(value.getBytes("ISO-8859-1"),"UTF-8");
//							} catch (UnsupportedEncodingException e) {
//								e.printStackTrace();
//							}    
//	            	    }
	            		paramMap.put(key.toString(), value);
					}
	            } 
	        }
			
			//Map<String, String[]> paramMap = request.getParameterMap();
			System.out.println(paramMap);
	        return paramMap;
	}
	protected void flushResponse(HttpServletResponse response, String responseContent) {
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("GBK");
			// 针对ajax中页面编码为GBK的情况，一定要加上以下两句
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/html;charset=UTF-8");
			writer = response.getWriter();
			if (responseContent==null || "".equals(responseContent) || "null".equals(responseContent)) {
				writer.write("");
			} else {
				writer.write(responseContent);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
	
	

	
	/**
	 * 获取session对象
	 * @return
	 */
	protected  HttpSession getSession(HttpServletRequest request){ 
		HttpSession session =request.getSession(); 
		return session;
	}
	public ModelAndView getPageNumberInfo(int total, int startIndex, int pageSize, ModelAndView result) {
		//Math.ceil整数则为该整数，Math.ceil小数则为靠近大的整数
        int current =  (int) Math.ceil((startIndex + 1.0) / pageSize);
        // startIndex, 0,pageSize 10,   1
        // startIndex, 1,pageSize 10,   1
        //………………
        //startIndex,11,pageSize 10    2
        //startIndex,100,pageSize 10  11
		result.addObject("start", startIndex);//其实记录
		result.addObject("limit", pageSize);//每页显示多少条
		result.addObject("total", total);//总的记录数
		result.addObject("current", current);
		if(total > 0) {
			//page:总的页数
			int page =  (int) Math.ceil(total/ pageSize);
			double totald= total;//   total:10  pageSize:3    3
			//totald = 10.0
			//3.333             3
			if(totald/pageSize>total/pageSize){
				page =page +1;
			}
			//等价
			//int  page = total%pageSize==0?total/pageSize:total/pageSize+1;
			
			result.addObject("page", page);
			
			int startPage = 0;  //页面的起始页码    1,2,3,4,5,
			int endPage = 0;// 页面的结尾页   100  与总的页数有关
			//page = 3,  startPage=1   endPage = 3;
			if (page<8) {
				startPage = 1;
				endPage = page;//7
			} else {
				//
				if (current<5) { //展示前6页
					startPage = 1;
					endPage = 6;//page>6?6:page;
				} else if (page-current<6) {//展示最后6页，例如：current=10 page=15   10-15
					startPage = page-5;
					endPage = page;
				} else {//current=20    18——22
					startPage = current - 2;
					endPage = current + 2;
				}
			}
			result.addObject("startPage", startPage);
			result.addObject("endPage", endPage);
		} else {
			result.addObject("page", 0);
		}
		return result;
	}
}
