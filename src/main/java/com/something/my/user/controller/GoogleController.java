package com.something.my.user.controller;

import com.something.my.user.service.GoogleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class GoogleController {

    private final GoogleService googleService;

    @GetMapping("/users/google")
    public ResponseEntity<String> google(
            @RequestParam String code
    ){
        log.info(code);
        String googleUser = googleService.getGoogleUser(code);
        return ResponseEntity.ok(googleUser);
    }
}
