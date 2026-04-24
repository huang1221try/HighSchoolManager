package com.highSchool.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

public interface HighBaseMapper<T> extends BaseMapper<T> {

    default LambdaQueryWrapper<T> lambdaQuery(){
        return Wrappers.lambdaQuery();
    }

    default LambdaUpdateWrapper<T> lambdaUpdate(){
        return Wrappers.lambdaUpdate();
    }



}
