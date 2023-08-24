package com.lc.lms.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lms.pojo.Admin;
import com.lc.lms.pojo.Book;
import com.lc.lms.pojo.Borrow;
import com.lc.lms.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface BorrowService extends IService<Borrow> {
    public int generateNewBorrow(Book book, User user);
    public int backBook(Book book);

}
