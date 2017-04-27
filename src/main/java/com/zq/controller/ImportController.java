package com.zq.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.zq.common.BaseController;
import com.zq.common.excel.ExcelUtil;
import com.zq.service.OrgService;
import com.zq.service.UserService;


@Controller
@RequestMapping("/importExcel")
public class ImportController   extends BaseController{
	
	@Resource
	UserService userService;
	@Resource
	OrgService orgService;
	
	//文件上传 
	/**
	 *1、 springmvc--:配置文件上传的解析器
	 *2、
	 * @param response
	 */
	@RequestMapping("/uploadExcel")
	public void importUser(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			// 获取文件对象
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 文件列表
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				// 单个文件
				MultipartFile file = entity.getValue();//值  文件对象
				InputStream in = file.getInputStream();
				// 读取单元格---->List<Map<String,Object>>
				List<Map<String, Object>> list = this.readExcel(in);
				//组织名  但是数据库User表，没有组织名字段  只有组织id
				for(int i = 0;i<list.size();i++){
					Map<String, Object> excelUser  = list.get(i);
					//得到组织名称
					String orgName = excelUser.get("orgName").toString();
					//根据组织名称查询组织编号
					Object orgId = orgService.selectOrgByName(orgName);
					if(orgId!=null){
						excelUser.put("orgId", orgId);
					}else{
						excelUser.put("orgId", -1);//查不出组织id  给默认值为-1
					}
					Object sex = excelUser.get("userSex");//数据库 1,2，3 
	    			if(sex!=null&&!"".equals(sex)){
	    				String sexStr = sex.toString();
	    				if("男".equals(sexStr)){
	    					excelUser.put("userSex", "1");
	    				}
	    				if("女".equals(sexStr)){
	    					excelUser.put("userSex", "2");
	    				}
	    				if("保密".equals(sexStr)){
	    					excelUser.put("userSex", "3");
	    				}
	    			}else{
	    				excelUser.put("userSex", null);
	    			}										
				}	
				
				//调用Service的方法  进行批量导入
				userService.importUser(list);
				
				retMap.put("success", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			retMap.put("success", false);
		}

		Gson gson = new Gson();
		String resultJson = gson.toJson(retMap);
		this.flushResponse(response, resultJson);
		
	}
	
	/**
	 * 对excel文档进行读取
	 */
	public List<Map<String, Object>> readExcel(InputStream in) throws Exception {
		int startIndex = 2;
		//列的英文名称
		String fieldsStr = "userChName,mobilePhone,email,userSex,userName,orgName,provinceName,cityName";
		String[] fields = fieldsStr.split(",");
		ExcelUtil util = new ExcelUtil();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		//通过WorkbookFactory对象获取WorkBook对象
		Workbook wwb = WorkbookFactory.create(in);
		
		// 只读取第一个sheet页
		if (wwb != null && wwb.getNumberOfSheets() > 0) {//非空判断  判断是否用sheet表格
			Sheet wsheet = wwb.getSheetAt(0);//得到第一个Sheet表格对象
			//String charAt(0);
			dataList = util.readSimple(wsheet,startIndex, fields);
		}
		return dataList;
	}

}
