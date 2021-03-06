package com.zq.service;

import java.util.List;
import java.util.Map;

import com.zq.pojo.Org;

public interface OrgService {
    int deleteByPrimaryKey(Long orgId);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Long orgId);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);

	List<Org> findOrgByParentId(Map<String, Object> paramMap);

	Object selectOrgByName(String orgName);
}