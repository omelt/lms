package com.lc.lms.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lms.pojo.Book;
import com.lc.lms.pojo.User;

public interface BookService extends IService<Book> {
    public int destoryBookByBookNumber(Book book);
    public int chanageBookMegByBookNumber(Book book);
}
