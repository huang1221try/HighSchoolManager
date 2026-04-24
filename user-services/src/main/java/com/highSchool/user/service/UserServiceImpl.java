package com.highSchool.user.service;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.highSchool.common.dto.UserRoleAndPermission;
import com.highSchool.common.entity.Permission;
import com.highSchool.common.entity.Role;
import com.highSchool.common.entity.User;
import com.highSchool.common.mapper.PermissionMapper;
import com.highSchool.common.mapper.RoleMapper;
import com.highSchool.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

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
