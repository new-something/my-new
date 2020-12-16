package com.something.my.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    @GetMapping("/u/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/s/logout")
    public RedirectView logout(
            HttpSession session
    ){
        session.invalidate();
        return new RedirectView("/");
    }
}
