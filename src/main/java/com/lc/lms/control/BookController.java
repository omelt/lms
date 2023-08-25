package com.lc.lms.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lc.lms.Service.BookService;
import com.lc.lms.annotate.IsAdmin;
import com.lc.lms.pojo.Book;
import com.lc.lms.util.ResponseEntity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "查找书籍", notes = "根据信息模糊查找只写一个就行")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookName", value = "书名", required = false, defaultValue = ""),
            @ApiImplicitParam(name = "bookAuthor", value = "作者", required = false, defaultValue = ""),
            @ApiImplicitParam(name = "bookPress", value = "出版社", required = false, defaultValue = "")
    })
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

    @ApiOperation(value = "添加书籍", notes = "给定书籍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookName", value = "书名", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "bookAuthor", value = "作者", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "bookPress", value = "出版社", required = true, defaultValue = "")
    })
    @PostMapping(value = "/warehousing")
    public ResponseEntity bookWarehousing (Book book){

        if(!bookService.save(book)){
            return new ResponseEntity(HttpStatus.OK.value(), "check for blank space and length", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "done", null);
    }

    @ApiOperation(value = "删除书籍", notes = "退出老旧的书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookNumber", value = "书籍编号", required = true, defaultValue = ""),
    })
    @PostMapping(value = "/destory")
    public ResponseEntity bookDestory (Book book){
        if(bookService.destoryBookByBookNumber(book)>0){
            return new ResponseEntity(HttpStatus.OK.value(), "book is gone", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "delete error", null);
    }

    @ApiOperation(value = "修改书籍信息", notes = "部分书籍可能登记错误 除去编号外 哪部分错误填入哪部分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookNumber", value = "书籍编号", required = true, defaultValue = ""),
    })
    @PostMapping(value = "/change")
    public ResponseEntity bookChage (Book book){
        if(bookService.chanageBookMegByBookNumber(book)>0){
            return new ResponseEntity(HttpStatus.OK.value(), "book is fix", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "error", null);
    }

}
