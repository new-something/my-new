package com.something.my.app.controller;

import com.something.my.app.controller.dto.CreateUrlRedirectionRequest;
import com.something.my.app.domain.UrlRedirection;
import com.something.my.app.service.UrlRedirectionService;
import com.something.my.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

import static com.something.my.global.utils.Keys.SESSION;

@RestController
@RequiredArgsConstructor
public class UrlRedirectionRestController {

    private final UrlRedirectionService urlRedirectionService;

    @GetMapping("/apis/url-redirections/{id}")
    public ResponseEntity<?> findById(
            @PathVariable Long id
    ){
        UrlRedirection urlRedirection = urlRedirectionService.findById(id);
        return ResponseEntity.ok(urlRedirection);
    }

    @PostMapping("/apis/url-redirections")
    public ResponseEntity<Void> create(
            HttpServletRequest req,
            @RequestBody CreateUrlRedirectionRequest request
    ) throws URISyntaxException {
        User session = (User) req.getSession().getAttribute(SESSION);
        UrlRedirection urlRedirection = urlRedirectionService.create(request.getPath(), session, request.getDestinationUrl());
        return ResponseEntity.created(new URI("/apis/url-redirections/" + urlRedirection.getUrlRedirectionId())).build();
    }
}
