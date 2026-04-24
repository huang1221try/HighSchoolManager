package com.highSchool.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.highSchool.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends HighBaseMapper<User> {

    @Select("select * from sys_user where username = #{username}")
    User getUser(@Param("username") String username);

}
