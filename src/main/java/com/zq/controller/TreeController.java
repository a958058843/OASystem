package com.zq.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zq.common.BaseController;
import com.zq.pojo.Org;
import com.zq.service.OrgService;

@Controller
@RequestMapping("/tree")
public class TreeController extends BaseController {
	@Resource
	private OrgService orgService;

	@RequestMapping("/orgSubList")
	public void orgSubList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = super.getParam(request);
		Object id = paramMap.get("id");
		if (id == null || id == "") {
			id = "-1";
		}
		paramMap.put("id", Integer.parseInt(id + ""));
		List<Org> resultList = orgService.findOrgByParentId(paramMap);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Org org : resultList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", org.getOrgId());
			map.put("name", org.getOrgName());
			map.put("isParent", true);
			list.add(map);
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		super.flushResponse(response, json);
	}
}
