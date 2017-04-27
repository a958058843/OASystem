package com.zq.pojo;

import java.util.Date;

public class RoleOrgRel {
    private Long roleOrgRelId;

    private Long roleId;

    private Long orgId;

    private Date createdDate;

    public Long getRoleOrgRelId() {
        return roleOrgRelId;
    }

    public void setRoleOrgRelId(Long roleOrgRelId) {
        this.roleOrgRelId = roleOrgRelId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}