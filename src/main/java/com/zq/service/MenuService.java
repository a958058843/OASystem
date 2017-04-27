package com.zq.service;

import java.util.List;
import java.util.Map;

import com.zq.pojo.Menu;

public interface MenuService {
    int deleteByPrimaryKey(Long menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

	List<Menu> findAuthorizationMenuByRoleId(Map<String, String[]> map);
}