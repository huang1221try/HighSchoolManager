package com.highSchool.service;

import com.highSchool.dto.UserRoleAndPermission;
import com.highSchool.entity.Permission;
import com.highSchool.entity.Role;
import com.highSchool.entity.User;
import com.highSchool.mapper.PermissionMapper;
import com.highSchool.mapper.RoleMapper;
import com.highSchool.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public List<Role> getRoles(Long userId) {
        return roleMapper.getRolesByUserId(userId);
    }

    @Override
    public List<Permission> getPermissions(Long userId) {
        return permissionMapper.selectPermissionByUserId(userId);
    }

    @Override
    public UserRoleAndPermission getUserRoleAndPermission(Long userId) {
        UserRoleAndPermission userRoleAndPermission = new UserRoleAndPermission();
        userRoleAndPermission.setRoles(roleMapper.getRolesByUserId(userId));
        userRoleAndPermission.setPermissions(permissionMapper.selectPermissionByUserId(userId));
        return userRoleAndPermission;
    }
}
