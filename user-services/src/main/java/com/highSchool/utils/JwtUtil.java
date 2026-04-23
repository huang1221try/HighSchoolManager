package com.highSchool.utils;

import com.highSchool.constants.JwtConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {


    //生成token
    public String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstants.EXPIRE))
                .signWith(SignatureAlgorithm.HS512, JwtConstants.SECRET)
                .compact();
    }

    /**
     * 验证token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JwtConstants.SECRET).parseClaimsJwt(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取用户名
     */
    public  String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * 获取载荷
     */
    public  Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(JwtConstants.SECRET).parseClaimsJwt(token).getBody();
    }

    /**
     * 获取token过期时间
     */
    public Date getExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    /**
     * 判断token是否过期
     */
    public  Boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    /**
     * 判断token是否需要续签
     * 续签规则：最后30分钟访问自动续签
     */
    public Boolean isNeedRenew(String token) {
        //获取过期时间
        Date date = getExpiration(token);
        Long remain = date.getTime() - System.currentTimeMillis();
        return remain > 0 && remain < (30 * 60 * 1000);
    }


}
