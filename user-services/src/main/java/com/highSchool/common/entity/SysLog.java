package com.highSchool.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_log")
public class SysLog implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;
    private String module;
    private String oper;
    private String url;
    private String params;
    private String result;
    private String status;
    private String errorMsg;
    private Long costTime;
    private Date createTime;
}