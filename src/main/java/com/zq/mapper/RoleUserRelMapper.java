package com.zq.mapper;

import com.zq.pojo.RoleUserRel;

public interface RoleUserRelMapper {
    int deleteByPrimaryKey(Long roleUserRelId);

    int insert(RoleUserRel record);

    int insertSelective(RoleUserRel record);

    RoleUserRel selectByPrimaryKey(Long roleUserRelId);

    int updateByPrimaryKeySelective(RoleUserRel record);

    int updateByPrimaryKey(RoleUserRel record);

	String findRolesByUserId(Long userId);
}