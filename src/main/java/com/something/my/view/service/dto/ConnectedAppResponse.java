package com.something.my.view.service.dto;

import com.something.my.app.domain.ConnectedApp;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@ToString
public final class ConnectedAppResponse {
    private final long connectedId;
    private final long appCode;
    private final String appName;
    private final String appIcon;
    private final String domain;
    private final String description;

    private final Set<ShortcutResponse> shortcuts;

    public ConnectedAppResponse(ConnectedApp connectedApp) {
        this.connectedId = connectedApp.getConnectedId();
        this.appCode = connectedApp.getProvidedApp().getAppCode();
        this.appName = connectedApp.getProvidedApp().getAppName();
        this.appIcon = connectedApp.getProvidedApp().getAppIcon();
        this.domain = connectedApp.getProvidedApp().getDomain();
        this.description = connectedApp.getProvidedApp().getDescription();
        this.shortcuts = connectedApp.getShortcuts()
                .stream()
                .map(ShortcutResponse::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}
