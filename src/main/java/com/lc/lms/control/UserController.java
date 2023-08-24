package com.lc.lms.control;

import com.lc.lms.Service.UserService;
import com.lc.lms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
//    @GetMapping("/all")
    @ResponseBody
    public List<User> allUsers(){
        List<User> allUsers = userService.list();
        return allUsers;

    }
}
