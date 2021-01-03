package com.something.my.view.service;

import com.something.my.app.repository.UrlRedirectionRepository;
import com.something.my.user.domain.User;
import com.something.my.view.repository.ViewRepository;
import com.something.my.view.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewService {

    private final ViewRepository viewRepository;

    private final UrlRedirectionRepository urlRedirectionRepository;

    public DashboardData dashboardData(User session) {
        List<ConnectedAppInDashboard> connectedAppResponses = viewRepository.findConnectedAppsByUser(session)
                .stream()
                .map(ConnectedAppInDashboard::new)
                .collect(Collectors.toUnmodifiableList());

        List<ShortcutInDashboard> shortcutResponses = viewRepository.findShortcutsByUser(session)
                .stream()
                .map(ShortcutInDashboard::new)
                .collect(Collectors.toUnmodifiableList());

        List<UrlRedirectionInDashboard> urlRedirectionResponses = urlRedirectionRepository.findAllByUser(session)
                .stream()
                .map(UrlRedirectionInDashboard::new)
                .collect(Collectors.toUnmodifiableList());

        return new DashboardData(connectedAppResponses, shortcutResponses, urlRedirectionResponses);
    }
}
