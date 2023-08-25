package com.lc.lms.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lc.lms.Service.UserService;
import com.lc.lms.annotate.IsAdmin;
import com.lc.lms.pojo.User;
import com.lc.lms.util.ResponseEntity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@IsAdmin
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "查找用户", notes = "模糊搜索，只要三个都不空就行")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "名字", required = false, defaultValue = ""),
            @ApiImplicitParam(name = "userEmail", value = "邮箱", required = false, defaultValue = ""),
            @ApiImplicitParam(name = "userNumber", value = "用户识别码", required = false, defaultValue = ""),
    })
    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public ResponseEntity allUsers(String userName, String userEmail, String userNumber){
        QueryWrapper qw = new QueryWrapper();
        if(userNumber==null && userEmail==null && userName==null)
            return new ResponseEntity(HttpStatus.OK.value(), "Don't all blank", null) ;
        if(userName!=null) qw.like("user_name",'%'+userName+'%');
        if(userEmail!=null) qw.like("user_email",'%'+userEmail+'%');
        if(userNumber!=null) qw.like("user_number",'%'+userNumber+'%');
        List<User> allUsers = userService.list(qw);
        return new ResponseEntity(HttpStatus.OK.value(), "find done", allUsers);

    }
    @ApiOperation(value = "查看用户列表", notes = "")
    @RequestMapping(value = "/findALL",method = RequestMethod.GET)
    public ResponseEntity findAll(){
        return new ResponseEntity(HttpStatus.OK.value(), "find done", userService.list());
    }

    @ApiOperation(value = "消除用户", notes = "按照用户唯一标识处理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNumber", value = "用户标识码", required = true, defaultValue = ""),
    })
    @PostMapping(value = "/delect")
    public ResponseEntity delectOneUser(User user){
        if(user.getUserNumber()==null){
            return new ResponseEntity(HttpStatus.OK.value(), "message error", null);
        }
        if(userService.removeOneByUserNumber(user)==1){
            return new ResponseEntity(HttpStatus.OK.value(), "work done", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "fail", null);
    }

    @ApiOperation(value = "新增", notes = "填入必要信息，新建借书卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "名字", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "userEmail", value = "邮箱", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "userAge", value = "年龄", required = true, defaultValue = ""),
    })
    @PostMapping(value = "/add")
    public ResponseEntity regNewUser (User user){
        
        if(!userService.save(user)){
            return new ResponseEntity(HttpStatus.OK.value(), "check for blank space and length", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "done", null);
    }
    @ApiOperation(value = "修改用户信息", notes = "除去唯一用户标识码外，其他数据为变更项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNumber", value = "用户标识码", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "userName", value = "名字", required = false, defaultValue = ""),
            @ApiImplicitParam(name = "userEmail", value = "邮箱", required = false, defaultValue = ""),
            @ApiImplicitParam(name = "userAge", value = "年龄", required = false, defaultValue = ""),
            @ApiImplicitParam(name = "userLimit", value = "用户借书上限", required = false, defaultValue = ""),
    })
    @PostMapping(value = "/change")
    public ResponseEntity changeUser (User user){

        if(userService.updateOneBytUserNumber(user)>0){
            return new ResponseEntity(HttpStatus.OK.value(), "done", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "message error", null);
    }

}
