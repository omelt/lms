package com.lc.lms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.lms.pojo.User;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAll();
}
