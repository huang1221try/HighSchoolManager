package com.highSchool.filter;

import com.highSchool.constants.JwtConstants;
import com.highSchool.utils.JwtToken;
import com.highSchool.utils.JwtUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt过滤器
 */
public class JwtFilters extends BasicHttpAuthenticationFilter {

    private JwtUtil jwtUtil;

    private RedisTemplate<String, Object> redisTemplate;

    public JwtFilters(JwtUtil jwtUtil, RedisTemplate<String, Object> redisTemplate) {
        this.jwtUtil = jwtUtil;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            validFail(response);
            return false;
        }
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        //获取token
        String requestToken = getRequestToken(request);
        if (requestToken == null) {
            throw new RuntimeException("request token is null");
        }
        JwtToken jwtToken = new JwtToken(requestToken);
        //交给realm认证
        getSubject(request, response).login(jwtToken);

        //判断是否需要续签
        renewToken(requestToken, response);
        return true;
    }

    /**
     * 获取token
     * @param request
     */
    private String getRequestToken(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("header");
        if (header == null || !header.startsWith(JwtConstants.TOKEN_PREFIX)) {
            return null;
        }
        return header.replace(header, JwtConstants.TOKEN_PREFIX);
    }

    /**
     * 续签
     * @param response
     */
    private void renewToken(String token, ServletResponse response) {
        if (jwtUtil.isNeedRenew(token)) {
            String username = jwtUtil.getUsername(token);
            String newToken = jwtUtil.createToken(jwtUtil.getClaims(token), username);
            //旧token加入黑名单
            redisTemplate.opsForValue().set(JwtConstants.TOKEN_BLACKLIST + token,
                    "1", jwtUtil.getExpiration(token).getTime() - System.currentTimeMillis());
            ((HttpServletResponse) response).setHeader("new-token", newToken);
        }
    }

    protected void validFail(ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
