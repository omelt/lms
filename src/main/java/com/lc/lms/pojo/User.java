package com.lc.lms.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "`user`")
    public class User {

        @TableId(value = "user_id")
        private Long userId;


    @TableField(value = "user_name")
        private String userName;

    @TableField(value = "user_age")
        private Integer userAge;

    @TableField(value = "user_email")
        private String userEmail;





    }


