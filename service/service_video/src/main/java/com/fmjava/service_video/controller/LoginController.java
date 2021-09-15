package com.fmjava.service_video.controller;

import com.fmjava.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {
    @PostMapping("/login")
    public ResponseResult login(){
        return ResponseResult.ok().data("token","admin-token");
    }

    @GetMapping("/info")
    public ResponseResult info(){
        return ResponseResult.ok()
                .data("roles","['admin']")
                .data("name","yttidea")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
