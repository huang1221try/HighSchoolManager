package com.highSchool.user.form;

import com.highSchool.common.form.BaseForm;
import lombok.Data;

@Data
public class LoginForm extends BaseForm {

    private String username;

    private String password;

}
