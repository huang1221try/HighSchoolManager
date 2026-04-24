package com.highSchool.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.highSchool.common.annotation.Anonymous;
import com.highSchool.common.annotation.Permission;
import com.highSchool.common.form.BaseForm;
import com.highSchool.common.param.RequestParam;
import com.highSchool.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public abstract class BaseController {

    @Resource
    private ObjectMapper objectMapper;

    private final Map<String, Method>  methodMap;

    public BaseController() {
        methodMap = new HashMap<>();
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (BaseForm.class.isAssignableFrom(method.getParameterTypes()[0])) {
                methodMap.put(method.getName(), method);
            }
        }
    }


    @PostMapping("/execute")
    public Response<Object> execute(@RequestBody BaseForm baseForm) throws Exception {
        //获取方法名
        String oper =  baseForm.getOper();

        Method method = methodMap.get(oper);
        // 如果方法上有 @Anonymous，直接跳过所有权限、登录校验
        if (!method.isAnnotationPresent(Anonymous.class)) {
            Permission perm = method.getAnnotation(Permission.class);
            if (perm != null) {
                SecurityUtils.getSubject().checkPermission(perm.value());
            }
        }
        // ===================== 关键：自动转成子类类型 =====================
        Class<?> targetType = method.getParameterTypes()[0];
        BaseForm realForm = (BaseForm) objectMapper.convertValue(baseForm, targetType);
        log.info("调用方法：{}，参数：{}", oper, realForm);
        // 执行
        Object result = null;
        if (method.getReturnType() == void.class) {
            method.invoke(this, realForm);
        } else {
            result = method.invoke(this, realForm);
        }

        return Response.ok("200", null, result);
    }

}
