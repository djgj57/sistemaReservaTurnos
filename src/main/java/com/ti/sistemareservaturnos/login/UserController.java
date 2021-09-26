package com.ti.sistemareservaturnos.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String user(){
        return "<h1> Welcome User </h1>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h1> Welcome Admin </h1>";
    }

}
