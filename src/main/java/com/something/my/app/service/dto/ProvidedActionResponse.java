package com.something.my.app.service.dto;

import com.something.my.app.domain.ProvidedAction;
import com.something.my.app.domain.ProvidedActionType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class ProvidedActionResponse {

    private final long providedActionId;
    private final ProvidedActionType type;
    private final String url;
    private final String description;

    public ProvidedActionResponse(ProvidedAction providedAction) {
        this.providedActionId = providedAction.getProvidedActionId();
        this.type = providedAction.getType();
        this.url = providedAction.getUrl();
        this.description = providedAction.getDescription();
    }
}
