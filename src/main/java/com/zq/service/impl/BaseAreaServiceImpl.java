package com.zq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zq.mapper.BaseAreaMapper;
import com.zq.pojo.BaseArea;
import com.zq.service.BaseAreaService;
@Service
public class BaseAreaServiceImpl implements BaseAreaService {
	@Resource
	private BaseAreaMapper baseAreaMapper;
	public List<BaseArea> selectByParentId(String area_Parent_id) {
		return baseAreaMapper.selectByParentId(area_Parent_id);
	}

}
