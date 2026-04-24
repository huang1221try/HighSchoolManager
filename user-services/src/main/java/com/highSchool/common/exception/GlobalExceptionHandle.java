package com.highSchool.common.exception;

import com.highSchool.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {


    @ExceptionHandler(AuthenticationException.class)
    public Response handleAuthException(AuthenticationException e) {
        if (e instanceof IncorrectCredentialsException) return Response.fail("令牌无效");
        if (e instanceof ExpiredCredentialsException) return Response.fail("令牌过期");
        if (e instanceof LockedAccountException) return Response.fail("账号禁用/令牌失效");
        return Response.fail("认证失败：" + e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public Response handleAuthzException() {
        return Response.fail("无权限访问");
    }

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        return Response.fail("系统异常：" + e.getMessage());
    }

}
