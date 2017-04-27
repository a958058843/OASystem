package com.zq.service;

import org.springframework.stereotype.Service;

import com.zq.pojo.RoleUserRel;
@Service
public interface RoleUserRelService {
    int deleteByPrimaryKey(Long roleUserRelId);

    int insert(RoleUserRel record);

    int insertSelective(RoleUserRel record);

    RoleUserRel selectByPrimaryKey(Long roleUserRelId);

    int updateByPrimaryKeySelective(RoleUserRel record);

    int updateByPrimaryKey(RoleUserRel record);

	String findRolesByUserId(Long userId);
}