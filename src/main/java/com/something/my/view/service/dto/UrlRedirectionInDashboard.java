package com.something.my.view.service.dto;

import com.something.my.app.domain.UrlRedirection;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class UrlRedirectionInDashboard {
    private final long urlRedirectionId;
    private final String path;
    private final String destinationUrl;

    public UrlRedirectionInDashboard(UrlRedirection urlRedirection){
        this.urlRedirectionId = urlRedirection.getUrlRedirectionId();
        this.path = urlRedirection.getPath();
        this.destinationUrl = urlRedirection.getDestinationUrl();
    }
}
