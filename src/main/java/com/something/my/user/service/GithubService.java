package com.something.my.user.service;

import com.something.my.global.exception.GithubLoginException;
import com.something.my.user.domain.User;
import com.something.my.user.repository.UserRepository;
import com.something.my.user.service.dto.github.GithubAccessToken;
import com.something.my.user.service.dto.github.GithubUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Log4j2
@Service
@RequiredArgsConstructor
public class GithubService {
    //    로그인 url
    //    https://github.com/login/oauth/authorize?scope=read:user&client_id=2a433252e03305352ce2

    //    access token url
    //    https://github.com/login/oauth/access_token

    //    특정 user 정보 얻는 url
    //    Authorization: token OAUTH-TOKEN    GET https://api.github.com/user

    @Value("${github.clientId}")
    private String clientId;
    @Value(("${github.clientSecret}"))
    private String clientSecret;


    private final WebClient webClient;

    private final UserRepository userRepository;

    public GithubAccessToken accessToken(final String code) {
        try {
            return webClient.post()
                    .uri("https://github.com/login/oauth/access_token")
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .body(
                            BodyInserters.fromFormData("client_id", clientId)
                                    .with("client_secret", clientSecret)
                                    .with("code", code)
                    ).retrieve()
                    .bodyToMono(GithubAccessToken.class)
                    .flux()
                    .toStream()
                    .findFirst()
                    .orElse(GithubAccessToken.NONE);
        } catch (WebClientResponseException e) {
            throw new GithubLoginException(e.getRawStatusCode(), e.getStatusText(), e.getResponseBodyAsString(),
                    e.getMessage());
        }
    }

    public User user(String accessToken) {
        try {
            String auth = "token " + accessToken;
            GithubUser githubUser = webClient.get()
                    .uri("https://api.github.com/user")
                    .header(HttpHeaders.AUTHORIZATION, auth)
                    .retrieve()
                    .bodyToMono(GithubUser.class)
                    .flux()
                    .toStream()
                    .findFirst()
                    .orElse(GithubUser.NONE);

            log.info(githubUser);
            User user = githubUser.toUser();
            userRepository.save(user);
            return user;
        } catch (WebClientResponseException e) {
            throw new GithubLoginException(e.getRawStatusCode(), e.getStatusText(), e.getResponseBodyAsString(),
                    e.getMessage());
        }
    }
}
