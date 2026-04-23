package com.highSchool.common.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RequestParam {

    @ApiModelProperty("操作编码（方法名）")
    private String oper;

    @ApiModelProperty("业务参数")
    private Object params;
}