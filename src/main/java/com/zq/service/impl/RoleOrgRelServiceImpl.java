package com.zq.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zq.mapper.RoleOrgRelMapper;
import com.zq.pojo.RoleOrgRel;
import com.zq.service.RoleOrgRelService;
@Service
public class RoleOrgRelServiceImpl implements RoleOrgRelService {
	
	@Resource
	private RoleOrgRelMapper roleOrgRelMapper;
	public int deleteByPrimaryKey(Long roleOrgRelId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(RoleOrgRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(RoleOrgRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public RoleOrgRel selectByPrimaryKey(Long roleOrgRelId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(RoleOrgRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(RoleOrgRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String findRolesByOrgId(Long orgId) {
		return roleOrgRelMapper.findRolesByOrgId(orgId);
	}

}
