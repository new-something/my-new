package com.something.my.global;

import com.something.my.global.exception.GithubLoginException;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(GithubLoginException.class)
    public String handleError(GithubLoginException e, Model model) {
        model.addAttribute("statusCode", e.getStatusCode());
        model.addAttribute("statusText", e.getStatusText());
        model.addAttribute("responseBody", e.getResponseBody());
        model.addAttribute("message", e.getMessage());
        return "error/error";
    }

}
