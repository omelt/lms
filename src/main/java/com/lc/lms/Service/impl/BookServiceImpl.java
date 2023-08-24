package com.lc.lms.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lms.Service.BookService;
import com.lc.lms.Service.UserService;
import com.lc.lms.mapper.BookMapper;
import com.lc.lms.mapper.UserMapper;
import com.lc.lms.pojo.Book;
import com.lc.lms.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {


}
