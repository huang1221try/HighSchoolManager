package com.highSchool.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.highSchool.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {


    @Select("select distinct p.* from sys_permission p inner join sys_role_permission rp on rp.perm_id = p.id" +
            " inner join sys_user_role r on rp.role_id = r.role_id" +
            " where r.user_id = #{userId}")
    List<Permission> selectPermissionByUserId(Long userId);

}
