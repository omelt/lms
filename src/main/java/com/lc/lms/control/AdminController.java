package com.lc.lms.control;

import com.lc.lms.Service.AdminService;
import com.lc.lms.annotate.IsAdmin;
import com.lc.lms.pojo.Admin;
import com.lc.lms.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
@PreAuthorize("hasAuthority('ROLE_ROOT')")
public class AdminController {
    //仅仅允许新增删除
    @Autowired
    AdminService adminService;

    @PostMapping(value = "/add")
    public ResponseEntity addAdmin (Admin admin){

        if(!adminService.save(admin)){
            return new ResponseEntity(HttpStatus.OK.value(), "check for blank space and length", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "done", null);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity delAdmin (Admin admin){

        if(adminService.deleteNormalAdminByNumber(admin)>0){
            return new ResponseEntity(HttpStatus.OK.value(), "delete done", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "error", null);
    }

}
