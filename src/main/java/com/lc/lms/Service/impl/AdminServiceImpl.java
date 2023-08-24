package com.lc.lms.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lms.Service.AdminService;
import com.lc.lms.Service.BookService;
import com.lc.lms.mapper.AdminMapper;
import com.lc.lms.mapper.BookMapper;
import com.lc.lms.pojo.Admin;
import com.lc.lms.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminMapper.selectOne(Wrappers.<Admin>lambdaQuery()
                .eq(Admin::getAdminUsername, username));
        if(admin==null){
            throw new UsernameNotFoundException("username didn't exist");
        }
        return new User(admin.getAdminUsername(),admin.getAdminPassword(),admin.getRole());
    }
}
