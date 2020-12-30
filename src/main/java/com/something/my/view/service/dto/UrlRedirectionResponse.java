package com.something.my.view.service.dto;

import com.something.my.app.domain.UrlRedirection;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class UrlRedirectionResponse {
    private long urlRedirectionId;
    private String path;
    private String destinationUrl;

    public UrlRedirectionResponse(UrlRedirection urlRedirection){
        this.urlRedirectionId = urlRedirection.getUrlRedirectionId();
        this.path = urlRedirection.getPath();
        this.destinationUrl = urlRedirection.getDestinationUrl();
    }
}
