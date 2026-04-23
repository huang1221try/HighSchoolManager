package com.highSchool.common.controller;

import com.highSchool.common.annotation.Anonymous;
import com.highSchool.common.annotation.Permission;
import com.highSchool.common.param.RequestParam;
import com.highSchool.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public abstract class BaseController {

    private final Map<String, Method>  methodMap;

    public BaseController() {
        methodMap = new HashMap<>();
        for (Method method : this.getClass().getDeclaredMethods()) {
            methodMap.put(method.getName(), method);
        }
    }


    @PostMapping("/execute")
    public Response<Object> execute(@RequestBody RequestParam requestParam) throws Exception {
        //获取方法名
        String oper =  requestParam.getOper();
        //获取参数
        Object param = requestParam.getParams();
        log.info("调用方法：{}，参数：{}", oper, param);

        Method method = methodMap.get(oper);
        // 如果方法上有 @Anonymous，直接跳过所有权限、登录校验
        if (!method.isAnnotationPresent(Anonymous.class)) {
            Permission perm = method.getAnnotation(Permission.class);
            if (perm != null) {
                SecurityUtils.getSubject().checkPermission(perm.value());
            }
        }
        Object result =  null;
        if (method.getReturnType().equals(void.class)) {
            Response.ok("200", null);
        } else {
            result = method.invoke(this, param);
        }

        return Response.ok("200", null, result);
    }

}
