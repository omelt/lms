package com.lc.lms.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lms.Service.UserService;
import com.lc.lms.mapper.UserMapper;
import com.lc.lms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int removeOneByUserNumber(User user) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_number",user.getUserNumber());
        return userMapper.delete(queryWrapper);
    }

    @Override
    public int updateOneBytUserNumber(User user) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_number",user.getUserNumber());
        return userMapper.update(user,queryWrapper);
    }
}
