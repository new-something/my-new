package com.something.my.view.controller;

import com.something.my.user.domain.User;
import com.something.my.view.service.ViewService;
import com.something.my.view.service.dto.DashboardData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.something.my.global.utils.Keys.SESSION;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ViewService viewService;

    @GetMapping("/u/dashboard")
    public String dashboard(
            HttpServletRequest request,
            Model model
    ){
        User session = (User) request.getSession().getAttribute(SESSION);
        DashboardData dashBoardData = viewService.dashboardData(session);
        model.addAttribute("dashboardData", dashBoardData);
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
