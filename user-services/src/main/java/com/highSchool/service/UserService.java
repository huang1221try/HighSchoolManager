package com.highSchool.service;

import com.highSchool.dto.UserRoleAndPermission;
import com.highSchool.entity.Permission;
import com.highSchool.entity.Role;
import com.highSchool.entity.User;

import java.util.List;

public interface UserService {

    User getUser(String username);

    List<Role> getRoles(Long userId);

    List<Permission> getPermissions(Long userId);

    UserRoleAndPermission getUserRoleAndPermission(Long userId);
}
