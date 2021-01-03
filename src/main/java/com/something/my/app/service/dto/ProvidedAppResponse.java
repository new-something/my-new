package com.something.my.app.service.dto;

import com.something.my.app.domain.ProvidedApp;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class ProvidedAppResponse {
    private final long appCode;
    private final String appName;
    private final String appIcon;
    private final String domain;
    private final String description;

    private boolean connected;

    public ProvidedAppResponse(ProvidedApp providedApp) {
        this.appCode = providedApp.getAppCode();
        this.appName = providedApp.getAppName();
        this.appIcon = providedApp.getAppIcon();
        this.domain = providedApp.getDomain();
        this.description = providedApp.getDescription();
        this.connected = false;
    }

    public void connected() {
        this.connected = true;
    }
}
