package com.zq.pojo;

import java.util.Date;

public class RoleMenuRel {
    private Long roleMenuRel;

    private Long roleId;

    private Long menuId;

    private Date createdDate;

    public Long getRoleMenuRel() {
        return roleMenuRel;
    }

    public void setRoleMenuRel(Long roleMenuRel) {
        this.roleMenuRel = roleMenuRel;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}