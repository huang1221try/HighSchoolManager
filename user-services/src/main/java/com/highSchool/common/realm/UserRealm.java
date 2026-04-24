package com.highSchool.common.realm;

import com.highSchool.common.constants.JwtConstants;
import com.highSchool.common.dto.UserRoleAndPermission;
import com.highSchool.common.entity.Permission;
import com.highSchool.common.entity.Role;
import com.highSchool.common.entity.User;
import com.highSchool.user.service.UserService;
import com.highSchool.common.utils.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@Component
public class UserRealm extends AuthorizingRealm {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String username = jwtUtil.getUsername(principals.toString());
        //获取用户权限
        User user = userService.getUser(username);
        if (user == null || user.getStatus() == 0) {
            throw new RuntimeException("账号不存在或被禁用，请联系管理员！");
        }
        UserRoleAndPermission userRoleAndPermission = userService.getUserRoleAndPermission(user.getId());
        info.setRoles(userRoleAndPermission.getRoles().stream().map(Role::getRoleCode).collect(Collectors.toSet()));
        info.setStringPermissions(userRoleAndPermission.getPermissions().stream().map(Permission::getPermCode).collect(Collectors.toSet()));
        return info;
    }

    /**
     * 认证
     * @param auth the authentication token containing the user's principal and credentials.
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token =  (String) auth.getPrincipal();
        //验证token是否有效
        if (!jwtUtil.validateToken(token)) {
            throw new RuntimeException("令牌无效！");
        }
        //判断token是否过期
        if (jwtUtil.isTokenExpired(token)) {
            throw new RuntimeException("登录过期，请重新登录！");
        }
        //判断token是否加入黑名单
        if (redisTemplate.hasKey(JwtConstants.TOKEN_BLACKLIST + token)) {
            throw new RuntimeException("令牌已失效，请重新登录！");
        }
        //判断用户是否被禁用
        String username = jwtUtil.getUsername(token);
        User user = userService.getUser(username);
        if (user == null || user.getStatus() == 0) {
            throw new RuntimeException("账号不存在或被禁用，请联系管理员！");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
