package com.something.my.user.controller;

import com.something.my.user.domain.User;
import com.something.my.user.service.GithubService;
import com.something.my.user.service.dto.github.GithubAccessToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/github/access-token")
    public ResponseEntity<GithubAccessToken> githubAccessToken(
            @RequestParam String code
    )
    {
        log.info(code);
        return ResponseEntity.ok(githubService.accessToken(code));
    }

    @GetMapping("/github/login/complete")
    public ModelAndView githubLoginComplete(
            HttpServletRequest request,
            @RequestParam String accessToken
    ){
        User user = githubService.user(accessToken);
        request.getSession().setAttribute("session", user);
        return new ModelAndView();
    }
}

