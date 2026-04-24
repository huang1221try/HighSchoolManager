package com.highSchool.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseForm {
    @ApiModelProperty("操作编码（方法名）")
    private String oper;

}
