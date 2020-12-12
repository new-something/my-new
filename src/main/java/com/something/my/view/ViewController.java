package com.something.my.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/u/dashboard")
    public String dashboard(){
        return "dashboard";
    }
}
