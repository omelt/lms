package com.lc.lms.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName(value = "`book`")
public class Book {
    private int bookId;
    private String bookNumber;
    private String bookName;
    private String bookAuthor;
    private String bookPress;
    @TableField(value = "gmt_creat")
    private Date creatTime;
}
