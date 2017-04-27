package com.zq.mapper;

import java.util.List;
import java.util.Map;

import com.zq.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(Map<String, Object> map);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(Map<String, Object> map);

	User selectByUser(User record);

	List findPageUser(Map<String, Object> paramMap);

	long getTotalCount(Map<String, Object> paramMap);

	List<Map<String, Object>> exportUser(Map<String, Object> paramMap);

	void importUser(List<Map<String, Object>> list);

	List<Map<String, Object>> selectBarProviceSexInfo();

	List<Map<String, Object>> selectProvinceInfo();

}