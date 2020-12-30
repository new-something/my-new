package com.something.my.view.service;

import com.something.my.app.repository.UrlRedirectionRepository;
import com.something.my.user.domain.User;
import com.something.my.view.repository.ViewRepository;
import com.something.my.view.service.dto.ConnectedAppResponse;
import com.something.my.view.service.dto.DashBoardData;
import com.something.my.view.service.dto.UrlRedirectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewService {

    private final ViewRepository viewRepository;

    private final UrlRedirectionRepository urlRedirectionRepository;

    public DashBoardData dashboardData(User session) {
        List<ConnectedAppResponse> connectedAppResponses = viewRepository.findConnectedAppsByUser(session)
                .stream()
                .map(ConnectedAppResponse::new)
                .collect(Collectors.toUnmodifiableList());

        List<UrlRedirectionResponse> urlRedirectionResponses = urlRedirectionRepository.findAllByUser(session)
                .stream()
                .map(UrlRedirectionResponse::new)
                .collect(Collectors.toUnmodifiableList());

        return new DashBoardData(connectedAppResponses, urlRedirectionResponses);
    }
}
