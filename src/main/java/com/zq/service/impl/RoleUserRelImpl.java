package com.zq.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zq.mapper.RoleUserRelMapper;
import com.zq.pojo.RoleUserRel;
import com.zq.service.RoleUserRelService;
@Service
public class RoleUserRelImpl implements RoleUserRelService {
	
	@Resource
	private RoleUserRelMapper roleUserRelMapper;
	
	public int deleteByPrimaryKey(Long roleUserRelId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(RoleUserRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(RoleUserRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public RoleUserRel selectByPrimaryKey(Long roleUserRelId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(RoleUserRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(RoleUserRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String findRolesByUserId(Long userId) {
		return roleUserRelMapper.findRolesByUserId(userId);
	}

}
