package com.something.my.app.controller.dto;

import com.something.my.global.exception.ServiceException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateShortcutRequest {
    private static final Pattern KEYWORD_PATTERN = Pattern.compile("^[a-z]{2,}(/[a-z]+)?$");

    private Long connectedId;
    private Long providedActionId;
    private String shortcutKeyword;

    public void validate() {
        if (Objects.isNull(connectedId)) throw new ServiceException(400, "필수값이 누락되었습니다.");
        if (Objects.isNull(providedActionId)) throw new ServiceException(400, "필수값이 누락되었습니다.");
        if (Objects.isNull(shortcutKeyword)) throw new ServiceException(400, "keyword 가 누락되었습니다.");

        if (!KEYWORD_PATTERN.matcher(shortcutKeyword).matches()) throw new ServiceException(400, "keyword 가 적합하지 않습니다.");
    }
}
