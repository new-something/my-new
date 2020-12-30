package com.something.my.app.service;

import com.something.my.app.domain.Shortcut;
import com.something.my.app.domain.ShortcutType;
import com.something.my.app.repository.ShortcutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShortcutService {

    private final ShortcutRepository shortcutRepository;

    @Transactional
    public Shortcut create(String path, String destinationUrl, ShortcutType type) {
        Shortcut shortcut = Shortcut.builder()
                .path(path)
                .destinationUrl(destinationUrl)
                .type(type)
                .build();
        return shortcutRepository.save(shortcut);
    }
}
