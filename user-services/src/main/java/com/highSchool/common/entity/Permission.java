package com.highSchool.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_permission")
public class Permission {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 权限列表
     */
    private String permCode; // user:add、user:list
    /**
     * 权限名称
     */
    private String permName;

}
