package com.highSchool.common.dto;

import com.highSchool.common.entity.Permission;
import com.highSchool.common.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserRoleAndPermission implements Serializable {
    /**
     * 角色列表
     */
    List<Role> roles;
    /**
     * 权限列表
     */
    List<Permission> permissions;


}
