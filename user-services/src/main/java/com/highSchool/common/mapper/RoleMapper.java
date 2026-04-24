package com.highSchool.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.highSchool.common.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT r.* " +
            "FROM sys_user_role ur " +
            "INNER JOIN sys_role r ON ur.role_id = r.id " +
            "WHERE ur.user_id = #{userId}")
    List<Role> getRolesByUserId(@Param("userId") Long userId);

}
