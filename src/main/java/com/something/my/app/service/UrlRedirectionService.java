package com.something.my.app.service;

import com.something.my.app.domain.UrlRedirection;
import com.something.my.app.repository.UrlRedirectionRepository;
import com.something.my.global.exception.ServiceException;
import com.something.my.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UrlRedirectionService {

    private final UrlRedirectionRepository urlRedirectionRepository;

    @Transactional
    public UrlRedirection create(String path, User user, String destination) {
        UrlRedirection urlRedirection = UrlRedirection.builder()
                .user(user)
                .path(path)
                .destinationUrl(destination)
                .build();

        return urlRedirectionRepository.save(urlRedirection);
    }

    public UrlRedirection findById(Long id) {
        return urlRedirectionRepository.findById(id)
                .orElseThrow(() -> new ServiceException(400, String.format("%d 의 자원이 존재하지 않습니다.", id)));
    }
}
