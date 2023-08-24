package com.lc.lms.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.naming.ldap.PagedResultsControl;
import java.sql.Date;

@Data
@TableName(value = "`borrow`",resultMap = "borrowMap")
public class Borrow {
    @TableId(value = "borrow_id",type = IdType.AUTO)
    private int borrowId;
    private int userId;
    private int bookId;
    private boolean renew;
    private boolean isReturn;
    private String borrowDate;
    private String backDate;
    @TableField(value = "gmt_creat")
    private Date creatTime;

    @TableField(exist = false)
    private User borrowUser;

    @TableField(exist = false)
    private Book borrowBook;

}
