package com.zq.service;

import java.util.List;

import com.zq.pojo.BaseArea;

public interface BaseAreaService {

	List<BaseArea> selectByParentId(String area_Parent_id);
	
	
}
