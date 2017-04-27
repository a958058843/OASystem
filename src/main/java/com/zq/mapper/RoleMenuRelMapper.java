package com.zq.mapper;

import com.zq.pojo.RoleMenuRel;

public interface RoleMenuRelMapper {
    int deleteByPrimaryKey(Long roleMenuRel);

    int insert(RoleMenuRel record);

    int insertSelective(RoleMenuRel record);

    RoleMenuRel selectByPrimaryKey(Long roleMenuRel);

    int updateByPrimaryKeySelective(RoleMenuRel record);

    int updateByPrimaryKey(RoleMenuRel record);
}