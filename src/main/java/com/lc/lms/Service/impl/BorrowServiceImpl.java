package com.lc.lms.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lms.Service.BorrowService;
import com.lc.lms.Service.UserService;
import com.lc.lms.mapper.BookMapper;
import com.lc.lms.mapper.BorrowMapper;
import com.lc.lms.mapper.UserMapper;
import com.lc.lms.pojo.Book;
import com.lc.lms.pojo.Borrow;
import com.lc.lms.pojo.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {

    @Autowired BorrowMapper borrowMapper;
    @Autowired UserMapper userMapper;
    @Autowired BookMapper bookMapper;

    @Override
    @Transactional
    public int generateNewBorrow(Book book, User user) {

        book.setBookEffect(0);
        user.setUserLimit(user.getUserLimit()-1);
        bookMapper.updateById(book);
        userMapper.updateById(user);


        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 30);

        Borrow borrow=new Borrow();

        borrow.setBookId(book.getBookId());
        borrow.setUserId(user.getUserId());
        borrow.setBackDate(sf.format(c.getTime()));
        borrow.setBorrowDate(sf.format(now));

        return borrowMapper.insert(borrow);
    }

    @Override
    @Transactional
    public int backBook(Book book) {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("book_number",book.getBookNumber());
        book=bookMapper.selectOne(queryWrapper);

        queryWrapper=new QueryWrapper();
        queryWrapper.eq("book_id",book.getBookId());
        queryWrapper.eq("is_return",false);

        Borrow borrow =borrowMapper.selectOne(queryWrapper);
        borrow.setReturn(true);

        User user=borrow.getBorrowUser();

        user.setUserLimit(user.getUserLimit()+1);
        book.setBookEffect(book.getBookEffect()+1);

        bookMapper.updateById(book);
        userMapper.updateById(user);
        return borrowMapper.updateById(borrow);


    }
}
