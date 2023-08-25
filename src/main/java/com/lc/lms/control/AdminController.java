package com.lc.lms.control;

import com.lc.lms.Service.AdminService;
import com.lc.lms.annotate.IsAdmin;
import com.lc.lms.pojo.Admin;
import com.lc.lms.util.ResponseEntity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "新增", notes = "填入名字,邮箱,年纪,用户名,密码即可")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminName", value = "名字", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "adminEmail", value = "邮箱", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "adminAge", value = "年纪", required = true, defaultValue = "0"),
            @ApiImplicitParam(name = "adminUsername", value = "用户名", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "adminPassword", value = "密码", required = true, defaultValue = ""),
    })
    public ResponseEntity addAdmin (Admin admin){

        if(!adminService.save(admin)){
            return new ResponseEntity(HttpStatus.OK.value(), "check for blank space and length", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "done", null);
    }


    @ApiOperation(value = "删除", notes = "根据管理员编号删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminNumber", value = "管理员编号", required = true, defaultValue = ""),
    })
    @PostMapping(value = "/delete")
    public ResponseEntity delAdmin (Admin admin){

        if(adminService.deleteNormalAdminByNumber(admin)>0){
            return new ResponseEntity(HttpStatus.OK.value(), "delete done", null);
        }
        return new ResponseEntity(HttpStatus.OK.value(), "error", null);
    }

}
