package com.highSchool.dto;

import com.highSchool.entity.Permission;
import com.highSchool.entity.Role;
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
