package com.something.my.app.service;

import com.something.my.app.domain.ConnectedApp;
import com.something.my.app.domain.ProvidedApp;
import com.something.my.app.repository.ConnectedAppRepository;
import com.something.my.app.repository.ProvidedAppRepository;
import com.something.my.global.exception.ServiceException;
import com.something.my.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ConnectedAppService {

    private final ProvidedAppRepository providedAppRepository;

    private final ConnectedAppRepository connectedAppRepository;

    @Transactional
    public ConnectedApp connect(long appCode, User user) {
        ProvidedApp providedApp = providedAppRepository.findById(appCode)
                .orElseThrow(() -> new ServiceException(400, String.format("존재하지 않는 앱 코드 : %d", appCode)));

        log.info(providedApp.getAppCode());
        log.info(providedApp.getAppName());
        log.info(user.getUserId());
        ConnectedApp connectedApp = ConnectedApp.builder()
                .providedApp(providedApp)
                .user(user)
                .build();

        connectedAppRepository.save(connectedApp);
        return connectedApp;
    }

    @Transactional
    public void disconnect(Long providedAppCode, User user) {
        ProvidedApp providedApp = ProvidedApp.of(providedAppCode);
        ConnectedApp connectedApp = connectedAppRepository.findByProvidedAppAndUser(providedApp, user)
                .orElseThrow(() -> new ServiceException(400, "기존에 연결되어 있지 않습니다."));

        connectedAppRepository.delete(connectedApp);
    }

    public ConnectedApp findById(long id) {
        return connectedAppRepository.findById(id)
                .orElseThrow(() -> new ServiceException(400, "app connection 이 존재하지 않습니다."));
    }
}
