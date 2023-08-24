package com.lc.lms.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lc.lms.Service.UserService;
import com.lc.lms.annotate.IsAdmin;
import com.lc.lms.pojo.User;
import com.lc.lms.util.ResponseEntity;
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
    @RequestMapping(value = "/findALL",method = RequestMethod.GET)
    public ResponseEntity findAll(){
        return new ResponseEntity(HttpStatus.OK.value(), "find done", userService.list());
    }

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

    @PostMapping(value = "/add")
    public ResponseEntity regNewUser (User user){
        
        if(!userService.save(user)){
            return new ResponseEntity(HttpStatus.OK.value(), "check for blank space and length", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "done", null);
    }

    @PostMapping(value = "/change")
    public ResponseEntity changeUser (User user){

        if(userService.updateOneBytUserNumber(user)>0){
            return new ResponseEntity(HttpStatus.OK.value(), "done", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "message error", null);
    }

}
