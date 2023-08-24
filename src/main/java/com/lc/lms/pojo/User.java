package com.lc.lms.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@TableName(value = "`user`")
public class User {

    @TableId(value = "user_id",type = IdType.AUTO)
    private int userId;

    @TableField(value = "user_number")
    private String userNumber;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "user_age")
    private Integer userAge;

    @TableField(value = "user_email")
    private String userEmail;

    @TableField(value = "user_limit")
    private int userLimit;

    @TableField(value = "gmt_creat")
    private Date creatTime;


}


