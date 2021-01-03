package com.something.my.app.service;

import com.something.my.app.domain.ConnectedApp;
import com.something.my.app.domain.ProvidedAction;
import com.something.my.app.domain.Shortcut;
import com.something.my.app.domain.ShortcutType;
import com.something.my.app.repository.ProvidedActionRepository;
import com.something.my.app.repository.ShortcutRepository;
import com.something.my.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShortcutService {

    private final ConnectedAppService connectedAppService;

    private final ProvidedActionRepository providedActionRepository;

    private final ShortcutRepository shortcutRepository;

    @Transactional
    public Shortcut create(long connectedId, String shortcutKeyword, long providedActionId) {
        ConnectedApp connectedApp = connectedAppService.findById(connectedId);

        ProvidedAction providedAction = providedActionRepository.findById(providedActionId)
                .orElseThrow(() -> new ServiceException(400, "존재하지 않는 액션입니다."));

        Shortcut shortcut = Shortcut.builder()
                .path(shortcutKeyword)
                .destinationUrl(providedAction.getUrl())
                .type(ShortcutType.LINK)
                .app(connectedApp)
                .appIcon(connectedApp.getProvidedApp().getAppIcon())
                .description(providedAction.getDescription())
                .build();
        return shortcutRepository.save(shortcut);
    }
}
