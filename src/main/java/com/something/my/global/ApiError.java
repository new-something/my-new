package com.something.my.global;

import lombok.Getter;

@Getter
public final class ApiError {

    private final String message;

    public ApiError(String message) {
        this.message = message;
    }
}
