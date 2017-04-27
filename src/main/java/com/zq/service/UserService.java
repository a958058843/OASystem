package com.zq.service;

import java.util.List;
import java.util.Map;

import com.zq.pojo.User;

public interface UserService {
    int deleteByPrimaryKey(Long userId);

    int insert(Map<String, Object> map);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(Map<String, Object> map);

	User selectByUser(User user);

	Map<String, Object> findPageUser(Map<String, Object> paramMap);

	List<Map<String, Object>> exportUser(Map<String, Object> paramMap);

	void importUser(List<Map<String, Object>> list);

	Map<String, Object> selectBarProviceSexInfo();

	Map<String, Object> selectProvinceInfo();
}