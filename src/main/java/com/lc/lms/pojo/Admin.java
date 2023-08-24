package com.lc.lms.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.sql.Date;
import java.util.Collection;

@Data
@TableName(value = "`admin`")
public class Admin {
    @TableId(value = "admin_id",type = IdType.AUTO)
    private int adminId;
    private String adminNumber;
    private String adminName;
    private String adminEmail;
    private int adminAge;
    private String adminUsername;
    private String adminPassword;
    @TableField(value = "admin_role")
    private Collection<? extends GrantedAuthority> role;
    @TableField(value = "gmt_creat")
    private Date creatTime;

    public Collection<? extends GrantedAuthority> getRole() {
        return role;
    }

    public void setRole(String s) {
        this.role = AuthorityUtils.commaSeparatedStringToAuthorityList(s);
    }
}
