package com.lc.lms.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lms.pojo.User;

public interface UserService extends IService<User> {
    public int removeOneByUserNumber(User user);

    public int updateOneBytUserNumber(User user);
}
