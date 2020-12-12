package com.something.my.user.controller;

import com.something.my.user.domain.User;
import com.something.my.user.service.GithubService;
import com.something.my.user.service.dto.github.GithubAccessToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/github/login")
    public RedirectView githubLogin(){
        return new RedirectView("https://github.com/login/oauth/authorize?scope=read:user&client_id=6d91f0584d549619c938");
    }

    @GetMapping("/github/redirect")
    public RedirectView githubAccessToken(
            @RequestParam String code
    )
    {
        log.info(code);
        GithubAccessToken githubAccessToken = githubService.accessToken(code);
        User user = githubService.user(githubAccessToken.getAccessToken());
        log.info("{}", user);
        return new RedirectView("/u/dashboard");
    }
}

