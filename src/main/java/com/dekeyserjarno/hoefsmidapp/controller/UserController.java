package com.dekeyserjarno.hoefsmidapp.controller;

import com.dekeyserjarno.hoefsmidapp.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public SecurityUtil securityUtil;

    @GetMapping("LogIn")
    public boolean logIn(String username,String password){
        return securityUtil.logIn(username,password);
    }

}
