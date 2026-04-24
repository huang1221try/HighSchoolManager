package com.highSchool.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.highSchool.common.dto.UserRoleAndPermission;
import com.highSchool.common.entity.Permission;
import com.highSchool.common.entity.Role;
import com.highSchool.common.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    User getUser(String username);

    List<Role> getRoles(Long userId);

    List<Permission> getPermissions(Long userId);

    UserRoleAndPermission getUserRoleAndPermission(Long userId);
}
