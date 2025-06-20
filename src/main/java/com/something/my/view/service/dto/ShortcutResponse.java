package com.something.my.view.service.dto;

import com.something.my.app.domain.Shortcut;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class ShortcutResponse {

    private final Long shortcutId;
    private final String path;
    private final String destinationUrl;
    private final String type;

    private final long connectedId;

    public ShortcutResponse(Shortcut shortcut) {
        this.shortcutId = shortcut.getShortcutId();
        this.path = shortcut.getPath();
        this.destinationUrl = shortcut.getDestinationUrl();
        this.type = shortcut.getType().name();
        this.connectedId = shortcut.getApp().getConnectedId();
    }
}
