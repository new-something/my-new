package com.something.my.view.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public final class DashboardData {

    private final List<ConnectedAppInDashboard> connectedApps;
    private final List<ShortcutInDashboard> shortcuts;
    private final List<UrlRedirectionInDashboard> urlRedirections;
}
