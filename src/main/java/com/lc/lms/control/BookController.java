package com.lc.lms.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lc.lms.Service.BookService;
import com.lc.lms.annotate.IsAdmin;
import com.lc.lms.pojo.Book;
import com.lc.lms.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
@IsAdmin
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public ResponseEntity findBook(String bookName, String bookAuthor, String bookPress){
        QueryWrapper qw = new QueryWrapper();
        if(bookName==null && bookAuthor==null && bookPress==null)
            return new ResponseEntity(HttpStatus.OK.value(), "Don't all blank", null) ;
        if(bookName!=null) qw.like("book_name",'%'+bookName+'%');
        if(bookAuthor!=null) qw.like("book_author",'%'+bookAuthor+'%');
        if(bookPress!=null) qw.like("book_press",'%'+bookPress+'%');
        List<Book> list = bookService.list(qw);
        return new ResponseEntity(HttpStatus.OK.value(), "find done", list);
    }

    @PostMapping(value = "/warehousing")
    public ResponseEntity bookWarehousing (Book book){

        if(!bookService.save(book)){
            return new ResponseEntity(HttpStatus.OK.value(), "check for blank space and length", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "done", null);
    }

    @PostMapping(value = "/destory")
    public ResponseEntity bookDestory (Book book){
        if(bookService.destoryBookByBookNumber(book)>0){
            return new ResponseEntity(HttpStatus.OK.value(), "book is gone", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "delete error", null);
    }

    @PostMapping(value = "/change")
    public ResponseEntity bookChage (Book book){
        if(bookService.chanageBookMegByBookNumber(book)>0){
            return new ResponseEntity(HttpStatus.OK.value(), "book is fix", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "error", null);
    }

}
