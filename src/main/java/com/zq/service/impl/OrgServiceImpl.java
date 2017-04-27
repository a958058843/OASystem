package com.zq.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zq.mapper.OrgMapper;
import com.zq.pojo.Org;
import com.zq.service.OrgService;
@Service
public class OrgServiceImpl implements OrgService {
	@Resource
	private OrgMapper orgMapper;
	public int deleteByPrimaryKey(Long orgId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Org record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Org record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Org selectByPrimaryKey(Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Org record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Org record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Org> findOrgByParentId(Map<String, Object> paramMap) {
		return orgMapper.findOrgByParentId(paramMap);
	}

	public Object selectOrgByName(String orgName) {
		return orgMapper.selectOrgByName(orgName);
	}

}
