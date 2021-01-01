package com.something.my.app.controller.dto;

import com.something.my.global.exception.ServiceException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConnectAppRequest {
    private Long appCode;

    public void validate(){
        if (Objects.isNull(appCode)) throw new ServiceException(400, "필수값이 누락되었습니다.");
    }
}
