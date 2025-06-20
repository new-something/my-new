package com.something.my.user.service.dto.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public final class GithubAccessToken {
    public static final GithubAccessToken NONE = new GithubAccessToken();
    private String accessToken;
    private String scope;
    private String tokenType;
}
