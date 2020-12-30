package com.something.my.view.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public final class DashBoardData {

    private final List<ConnectedAppResponse> connectedApps;
    private final List<UrlRedirectionResponse> urlRedirections;
}
