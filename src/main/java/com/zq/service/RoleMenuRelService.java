package com.zq.service;

import com.zq.pojo.RoleMenuRel;

public interface RoleMenuRelService {
    int deleteByPrimaryKey(Long roleMenuRel);

    int insert(RoleMenuRel record);

    int insertSelective(RoleMenuRel record);

    RoleMenuRel selectByPrimaryKey(Long roleMenuRel);

    int updateByPrimaryKeySelective(RoleMenuRel record);

    int updateByPrimaryKey(RoleMenuRel record);
}