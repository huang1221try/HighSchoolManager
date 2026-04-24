package com.highSchool.common.constants;

public class JwtConstants {

    public static final String SECRET = "HighSchool100101";
    //过期时间一小时
    public static final long EXPIRE = 3600L;
    //前缀
    public static final String TOKEN_PREFIX = "Bearer ";
    //token黑名单前缀
    public static final String TOKEN_BLACKLIST = "token_blacklist:";
    //用户权限缓存
    public static final String AUTH_ROLES = "auth_roles:";
}
