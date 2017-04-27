package com.zq.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zq.mapper.MenuMapper;
import com.zq.pojo.Menu;
import com.zq.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuMapper menuMapper;
	public int deleteByPrimaryKey(Long menuId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Menu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Menu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Menu selectByPrimaryKey(Long menuId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Menu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Menu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Menu> findAuthorizationMenuByRoleId(Map<String, String[]> map) {
		return menuMapper.findAuthorizationMenuByRoleId(map);
	}
    
}