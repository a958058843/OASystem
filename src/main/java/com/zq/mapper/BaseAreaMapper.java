package com.zq.mapper;

import java.util.List;

import com.zq.pojo.BaseArea;

public interface BaseAreaMapper {
    int deleteByPrimaryKey(Long areaPriId);

    int insert(BaseArea record);

    int insertSelective(BaseArea record);

    BaseArea selectByPrimaryKey(Long areaPriId);

    int updateByPrimaryKeySelective(BaseArea record);

    int updateByPrimaryKey(BaseArea record);

	List<BaseArea> selectByParentId(String area_Parent_id);
}