package com.zq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zq.mapper.UserMapper;
import com.zq.pojo.User;
import com.zq.service.UserService;

@Service("userService")
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	public int deleteByPrimaryKey(Long userId) {
		return userMapper.deleteByPrimaryKey(userId);
	}

	public int insert(Map<String, Object> map) {
		return userMapper.insert(map);
	}

	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public User selectByPrimaryKey(Long userId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}

	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Map<String, Object> map) {
		return userMapper.updateByPrimaryKey(map);
	}

	public User selectByUser(User record) {
		return userMapper.selectByUser(record);
	}

	public Map<String, Object> findPageUser(Map<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap();
		List<User> userList = userMapper.findPageUser(paramMap);
		// 2,求总的集合数
		long total = userMapper.getTotalCount(paramMap);
		resMap.put("userList", userList);
		resMap.put("total", total);
		return resMap;
	}

	public List<Map<String, Object>> exportUser(Map<String, Object> paramMap) {
		return userMapper.exportUser(paramMap);
	}

	public void importUser(List<Map<String, Object>> list) {
		userMapper.importUser(list);
	}

	public Map<String, Object> selectBarProviceSexInfo() {
		List<Map<String, Object>> list = userMapper.selectBarProviceSexInfo();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List provinceNameList = new ArrayList();
		List boyList = new ArrayList();
		List girlList = new ArrayList();
		List secrecyList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = list.get(i);
			Object name = map.get("provinceName");
			if(name==null||"".equals(name)){
				name="其他";
				map.put("provinceName", name);
			}
			provinceNameList.add(name);
			Object boyNum = map.get("boy");
			boyList.add(boyNum);
			Object girlNum = map.get("girl");
			girlList.add(girlNum);
			Object secrecyNum = map.get("secrecy");
			secrecyList.add(secrecyNum);
		}
		resultMap.put("provinceNameList", provinceNameList);
		resultMap.put("boyList", boyList);
		resultMap.put("girlList", girlList);
		resultMap.put("secrecyList", secrecyList);
		
		return resultMap;
	}

	public Map<String, Object> selectProvinceInfo() {
		List<Map<String, Object>> list = userMapper.selectProvinceInfo();//省份 人数
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List provinceNameList = new ArrayList();
		List userList = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String,Object> map = list.get(i);
			Object name = map.get("name");
			if(name==null||"".equals(name)){
				name="其他";
				map.put("name", name);
			}
			provinceNameList.add(name);
		}
		resultMap.put("legendData", provinceNameList);
		resultMap.put("seriesData", list);
		return resultMap;
	}

}
