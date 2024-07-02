package com.fzy.controller;

import com.fzy.service.UmsAdminService;
import com.fzy.vo.ResultJson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController

public class LoginController {
    @Resource
    UmsAdminService umsAdminService;
    @PostMapping("/login")
    ResultJson login(String username, String password) {
return null;
    }
}
