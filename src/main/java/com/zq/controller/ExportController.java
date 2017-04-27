package com.zq.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zq.common.BaseController;
import com.zq.common.excel.ExcelUtil;
import com.zq.common.excel.SimpleExportParameter;
import com.zq.service.UserService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/exportUserExcel")
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	System.out.println("导出excel");
    	String  fileName = "";
    	Map<String, Object> paramMap = this.getParam(request);
        Object fileNameObj = paramMap.get("fileName");
        
        //创建一个以年月日时分秒为名字的默认的文件名
        Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		String defaultFileName = formatter.format(date);//系统时间  “千锋员工信息2017-04-24-09-30-22”
		
        if(fileNameObj!=null&&!"".equals(fileNameObj.toString())){
        	String fileChName=fileNameObj.toString();
        	//如果带后缀则去掉
           if (fileChName.endsWith(".xls") || fileChName.endsWith(".xlsx")) {//员工信息表.xls
            	fileChName = fileChName.substring(0, fileChName.lastIndexOf("."));//员工信息表
            }
        	fileName = fileChName+defaultFileName;
        }else{
        	fileName = defaultFileName;
        }
        //导出的文件统一为.xls
        fileName = fileName +".xls";
        System.out.println("fileName:"+fileName);
        OutputStream os = null;
        try {
    		List<Map<String, Object>> dataList = userService.exportUser(paramMap);
    		
    		for(int i = 0;i<dataList.size();i++){
    			Map<String, Object> userMap = dataList.get(i);
    			Object sex = userMap.get("userSex");
    			if(sex!=null&&!"".equals(sex)){
    				String sexStr = sex.toString();
    				if("1".equals(sexStr)){
    					userMap.put("userSex", "男");
    				}
    				if("2".equals(sexStr)){
    					userMap.put("userSex", "女");
    				}
    				if("3".equals(sexStr)){
    					userMap.put("userSex", "保密");
    				}
    			}else{
    				userMap.put("userSex", "未知");
    			}
    		}
    		
    		System.out.println("---------excel导出部分---------");
    		// 下面的属性要与从数据库查出的map里key属性对应上（子集关系） dataList 中的Map的key一一对应
    		//excel的列的英文名
    		String fieldsIdStr = "userChName,orgName,userSex,mobilePhone,provinceName,cityName,contryName,userBirthday";
    		// 导出的excel文件中显示的名称,跟fieldsIdStr顺序一致，个数相等
    		String fieldsNameStr = "姓名,所属组织,性别,电话,省份,地市,区县,生日";
    		// 导出的excel文件的列宽，以上面的fieldsIdStr，fieldsNameStr个数相等
    		String widthsStr = "10,30,20,20,20,20,20,20";
    		// excel里面的标题
    		String title = "千锋员工信息";
    		// excel里面sheet的名称
    		String sheetName = "员工sheet";

    		String[] fieldsId = fieldsIdStr.split(",");//按照， 拆分为字符串数组
    		String[] fieldsName = fieldsNameStr.split(",");
    		String[] widths = widthsStr.split(",");

    		//封装导出参数的类
    		SimpleExportParameter parameters = new SimpleExportParameter();
    		parameters.setTitle(title);
    		parameters.setSheetName(sheetName);
    		parameters.setFieldsId(fieldsId);
    		parameters.setFieldsName(fieldsName);
    		parameters.setWidths(widths);
    		parameters.setDataList(dataList);

    		//导出excel的关键步骤
    		//1.创建一个以xls文件对应的类   excel文件
    		 Workbook wwb = new HSSFWorkbook();
    		//2.创建一个以xls文件sheet对应的类
    		Sheet wsheet = wwb.createSheet(parameters.getSheetName());
    		//3 row 
    		//4.Cell
    		//导入导出工具类
    		ExcelUtil utils = new ExcelUtil();
    		//以数据与Excel文档里的行跟单元格绑定
    		utils.simpleExport(wwb, wsheet, parameters);
    		 //Content-Type:参考网址：http://tool.oschina.net/commons
    		 //Content-Disposition 的作用，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字或类型。
            // 两个特殊的相应头：
            // ----Content-Type:       application/octet-stream 
            // ----Content-Disposition: attachment;filename=aaa.zip
        //文件下载  文件的类型：application/octet-stream 所有类型
    		//web 阶段：image/jpeg
    		response.setContentType("application/octet-stream; charset=utf-8");
        // 设置响应头  http协议
    		response.setHeader("Content-Disposition",
            		"attachment; filename=" +URLEncoder.encode(fileName,"utf-8"));//new String(fileName.getBytes("ISO-8859-1"),"utf-8")
            os = response.getOutputStream();
            wwb.write(os);
            os.flush();
        } finally {
            if (os != null)
                os.close();
        }

    }
}
