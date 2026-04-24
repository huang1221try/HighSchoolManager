package com.highSchool.user.controller;

import com.highSchool.common.annotation.Anonymous;
import com.highSchool.common.controller.BaseController;
import com.highSchool.user.form.LoginForm;
import com.highSchool.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Login")
public class LoginController extends BaseController {

    @Resource
    private UserService userService;

    @Anonymous
    @PostMapping("/login")
    public Object login(LoginForm loginForm) {

        return null;
    }
}
