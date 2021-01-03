package com.something.my.app.service;

import com.something.my.app.domain.AppTagType;
import com.something.my.app.repository.AppRepository;
import com.something.my.app.service.dto.ConnectedAppResponse;
import com.something.my.app.service.dto.ProvidedAppResponse;
import com.something.my.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.something.my.app.domain.AppTagType.ALL;

@Log4j2
@Service
@RequiredArgsConstructor
public class AppQueryService {

    private final AppRepository appRepository;

    public List<ProvidedAppResponse> findProvidedApp(User session, String tagName) {
        AppTagType appTagType = AppTagType.valueOf(tagName);
        if (appTagType == ALL) {
            List<ConnectedAppResponse> connectedAppsByUser = appRepository.findConnectedAppsByUser(session)
                    .stream()
                    .map(ConnectedAppResponse::new)
                    .collect(Collectors.toList());

            List<ProvidedAppResponse> providedAppResponses = appRepository.findAllProvidedApps()
                    .stream()
                    .map(ProvidedAppResponse::new)
                    .collect(Collectors.toList());

            checkConnected(providedAppResponses, connectedAppsByUser);
            return providedAppResponses;
        }

        List<ConnectedAppResponse> connectedAppsByUserAndTag = appRepository.findConnectedAppsByUserAndTag(session, appTagType)
                .stream()
                .map(ConnectedAppResponse::new)
                .collect(Collectors.toList());

        List<ProvidedAppResponse> providedAppResponses = appRepository.findAllProvidedAppsByTag(appTagType)
                .stream()
                .map(ProvidedAppResponse::new)
                .collect(Collectors.toList());

        checkConnected(providedAppResponses, connectedAppsByUserAndTag);
        return providedAppResponses;
    }

    private void checkConnected(List<ProvidedAppResponse> providedAppResponses, List<ConnectedAppResponse> connectedApps) {
        for (ProvidedAppResponse providedAppResponse : providedAppResponses) {
            for (ConnectedAppResponse connectedApp : connectedApps) {
                if (connectedApp.getAppCode() == providedAppResponse.getAppCode()) {
                    log.info("{}, {}", connectedApp, providedAppResponse);
                    providedAppResponse.connected(connectedApp.getConnectedId());
                    break;
                }
            }

        }
    }
}
