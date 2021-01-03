package com.something.my.view.service.dto;

import com.something.my.app.domain.ConnectedApp;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class ConnectedAppInDashboard {
    private final long connectedId;
    private final long appCode;
    private final String appName;
    private final String appIcon;
    private final String domain;
    private final String description;

    public ConnectedAppInDashboard(ConnectedApp connectedApp) {
        this.connectedId = connectedApp.getConnectedId();
        this.appCode = connectedApp.getProvidedApp().getAppCode();
        this.appName = connectedApp.getProvidedApp().getAppName();
        this.appIcon = connectedApp.getProvidedApp().getAppIcon();
        this.domain = connectedApp.getProvidedApp().getDomain();
        this.description = connectedApp.getProvidedApp().getDescription();
    }
}
