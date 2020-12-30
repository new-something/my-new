package com.something.my.app.service.dto;

import com.something.my.app.domain.ConnectedApp;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class ConnectedAppResponse {
    private final Long appCode;
    private final String appName;
    private final String appIcon;
    private final String domain;
    private final String description;

    public ConnectedAppResponse(ConnectedApp connectedApp) {
        this.appCode = connectedApp.getProvidedApp().getAppCode();
        this.appName = connectedApp.getProvidedApp().getAppName();
        this.appIcon = connectedApp.getProvidedApp().getAppIcon();
        this.domain = connectedApp.getProvidedApp().getDomain();
        this.description = connectedApp.getProvidedApp().getDescription();
    }
}
