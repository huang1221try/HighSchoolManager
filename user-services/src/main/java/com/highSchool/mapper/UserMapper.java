package com.highSchool.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.highSchool.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from sys_user where username = #{username}")
    User getUser(@Param("username") String username);

}
