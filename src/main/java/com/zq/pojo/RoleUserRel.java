package com.zq.pojo;

import java.util.Date;

public class RoleUserRel {
    private Long roleUserRelId;

    private Long roleId;

    private Long userId;

    private Date createdDate;

    public Long getRoleUserRelId() {
        return roleUserRelId;
    }

    public void setRoleUserRelId(Long roleUserRelId) {
        this.roleUserRelId = roleUserRelId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}