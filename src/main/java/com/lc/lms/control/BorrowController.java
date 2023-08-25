package com.lc.lms.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lc.lms.Service.BookService;
import com.lc.lms.Service.BorrowService;
import com.lc.lms.Service.UserService;
import com.lc.lms.annotate.IsAdmin;
import com.lc.lms.pojo.Book;
import com.lc.lms.pojo.Borrow;
import com.lc.lms.pojo.User;
import com.lc.lms.util.ResponseEntity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping(value = "/borrow")
@IsAdmin
public class BorrowController {
    @Autowired BorrowService borrowService;

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "申请借书", notes = "用户借书，需要用户认证和书本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookId", value = "书本id", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, defaultValue = "")
    })
    @PostMapping(value = "/apply")
    public ResponseEntity borrowBook(Book book, User user){
        book=bookService.getById(book.getBookId());
        user=userService.getById(user.getUserId());
        if(book.getBookEffect()>0){
            if(user.getUserLimit()>0){
                int res=borrowService.generateNewBorrow(book,user);
                if(res>0){
                    return new ResponseEntity(HttpStatus.OK.value(), "work done", null);
                }
                return new ResponseEntity(HttpStatus.OK.value(), "error,please check and try again", null);
            }
            return new ResponseEntity(HttpStatus.OK.value(), "user can not have more book", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "book is not effective", null);
    }

    @ApiOperation(value = "还书", notes = "归还书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookNumber", value = "书本编号", required = true, defaultValue = ""),
    })
    @PostMapping(value = "/back")
    public ResponseEntity backBook(Book book){
        if(borrowService.backBook(book)>0){
            return new ResponseEntity(HttpStatus.OK.value(), "book is back ", null);
        };
        return new ResponseEntity(HttpStatus.OK.value(), "error", null);
    }

    @ApiOperation(value = "续借", notes = "如果没借过可以再借一次")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookNumber", value = "书本编号", required = true, defaultValue = ""),
    })
    @PostMapping(value = "/renew")
    public ResponseEntity renewBook(Book book){
        if(book.getBookNumber()==null)  return new ResponseEntity(HttpStatus.OK.value(), "error message", null);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("book_number",book.getBookNumber());
        book=bookService.getOne(queryWrapper);

        queryWrapper = new QueryWrapper();
        queryWrapper.eq("book_id",book.getBookId());
        queryWrapper.eq("is_return",false);
        Borrow borrow=borrowService.getOne(queryWrapper);

        if(borrow.isRenew()){
            return new ResponseEntity(HttpStatus.OK.value(), "already renew", null);
        }

        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 15);
        Date after=c.getTime();

        if(now.compareTo(after)<=0) {
            return new ResponseEntity(HttpStatus.OK.value(), "must back date", null);
        }

        borrow.setRenew(true);
        borrow.setBackDate(sf.format(after));

        if(borrowService.updateById(borrow)){
            return new ResponseEntity(HttpStatus.OK.value(), "renew book is ok", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "error", null);
    }

    @ApiOperation(value = "查看借书记录", notes = "")
    @GetMapping(value = "/all")
    public ResponseEntity allBorrow( ){
        return new ResponseEntity(HttpStatus.OK.value(), "error", borrowService.list());
    }

}
