package com.something.my.global.exception;

import lombok.Getter;

@Getter
public class GithubLoginException extends RuntimeException {
    private final int statusCode;

    private final String statusText;

    private final String responseBody;

    private final String message;

    public GithubLoginException(int statusCode, String statusText, String responseBody, String message) {
        super(message);
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.responseBody = responseBody;
        this.message = message;
    }
}
