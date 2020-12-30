package com.something.my.app.controller.dto;

import com.something.my.global.exception.ServiceException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateUrlRedirectionRequest {
    private static final Pattern URL_PATTERN = Pattern.compile("[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)?");
    private String path;
    private String destinationUrl;

    public void validate(){
        if (Objects.isNull(path)) throw new ServiceException(400, "path 가 누락되었습니다.");
        if (Objects.isNull(destinationUrl)) throw new ServiceException(400, "destination url 이 누락되었습니다.");

        if (path.length() < 2 || path.length() > 30) throw new ServiceException(400, "path 길이가 적합하지 않습니다.");
        if (!URL_PATTERN.matcher(destinationUrl).matches()) throw new ServiceException(400, "destination url 이 적합하지 않습니다.");
    }

}
