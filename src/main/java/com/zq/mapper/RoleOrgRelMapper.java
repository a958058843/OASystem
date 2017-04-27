package com.zq.mapper;

import com.zq.pojo.RoleOrgRel;

public interface RoleOrgRelMapper {
    int deleteByPrimaryKey(Long roleOrgRelId);

    int insert(RoleOrgRel record);

    int insertSelective(RoleOrgRel record);

    RoleOrgRel selectByPrimaryKey(Long roleOrgRelId);

    int updateByPrimaryKeySelective(RoleOrgRel record);

    int updateByPrimaryKey(RoleOrgRel record);

	String findRolesByOrgId(Long orgId);
}