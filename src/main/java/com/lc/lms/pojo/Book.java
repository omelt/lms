package com.lc.lms.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName(value = "`book`")
public class Book {
    @TableId(value = "book_id",type = IdType.AUTO)
    private int bookId;
    private String bookNumber;
    private String bookName;
    private String bookAuthor;
    private String bookPress;
    private int bookEffect;
    @TableField(value = "gmt_creat")
    private Date creatTime;
}
