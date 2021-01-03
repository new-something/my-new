package com.something.my.view.service.dto;

import com.something.my.app.domain.Shortcut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ShortcutInDashboard {
    private final Long shortcutId;
    private final String path;
    private final String destinationUrl;
    private final String type;
    private final String description;
    private final String appIcon;

    private final long connectedId;

    public ShortcutInDashboard(Shortcut shortcut) {
        this.shortcutId = shortcut.getShortcutId();
        this.path = shortcut.getPath();
        this.destinationUrl = shortcut.getDestinationUrl();
        this.type = shortcut.getType().name();
        this.description = shortcut.getDescription();
        this.appIcon = shortcut.getAppIcon();
        this.connectedId = shortcut.getApp().getConnectedId();
    }
}
