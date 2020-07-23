package com.zh.security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherController {

    @GetMapping("/")
    public String index(){
        return "welcome";
    }

    @GetMapping("/userlogin")
    public String userlogin(){
        return "login";
    }


    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path")String path){
        return "level1/"+path;
    }
}
