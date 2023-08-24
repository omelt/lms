package com.lc.lms.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lms.pojo.Admin;
import com.lc.lms.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends IService<Admin>, UserDetailsService {
}
