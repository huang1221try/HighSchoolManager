package com.highSchool.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_user") // 对应数据库表名
public class User {

    // 主键自增
    @TableId(type = IdType.AUTO)
    private Long id;

    // 用户名（唯一）
    private String username;

    // 密码（BCrypt加密存储）
    private String password;

    // 盐值（可不用，BCrypt自带盐）
    private String salt;

    // 状态 1=正常 0=禁用
    private Integer status;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;
}
