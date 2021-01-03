package com.something.my.app.service;

import com.something.my.app.domain.ProvidedApp;
import com.something.my.app.repository.ProvidedActionRepository;
import com.something.my.app.service.dto.ProvidedActionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvidedActionService {

    private final ProvidedActionRepository providedActionRepository;

    public List<ProvidedActionResponse> findAllByAppCode(long appCode) {
        ProvidedApp providedApp = ProvidedApp.of(appCode);
        return providedActionRepository.findAllByProvidedApp(providedApp)
                .stream()
                .map(ProvidedActionResponse::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
