package com.something.my.global;

import com.something.my.global.exception.GithubLoginException;
import com.something.my.global.exception.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

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

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiError> serviceException(ServiceException e) {
        HttpStatus httpStatus = Objects.requireNonNullElse(HttpStatus.resolve(e.getStatusCode()), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(httpStatus).body(new ApiError(e.getMessage()));
    }
}
