package com.zq.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zq.common.BaseController;
import com.zq.pojo.BaseArea;
import com.zq.service.BaseAreaService;
@Controller
public class AreaController extends BaseController {
	
	@Resource
	private BaseAreaService baseAreaService;
	
	@RequestMapping("/getAreaList")
	public void getAreaList(HttpServletRequest request, HttpServletResponse response) {
		// 接到ajax传到controller的id
		String area_Parent_id = request.getParameter("areaParentId");
		//根据id查询base_area表
		List<BaseArea> list = baseAreaService.selectByParentId(area_Parent_id);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		super.flushResponse(response, json);
	}
}
