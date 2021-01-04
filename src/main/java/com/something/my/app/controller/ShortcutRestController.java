package com.something.my.app.controller;

import com.something.my.app.controller.dto.CreateShortcutRequest;
import com.something.my.app.domain.Shortcut;
import com.something.my.app.service.ShortcutService;
import com.something.my.view.service.dto.ShortcutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShortcutRestController {

    private final ShortcutService shortcutService;

    @GetMapping("/a/shortcuts")
    public ResponseEntity<?> findAllByAppId(
            @RequestParam(name = "app_code") Long appCode
    ) {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/a/shortcuts/{id}")
    public ResponseEntity<?> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/a/shortcuts")
    public ResponseEntity<ShortcutResponse> create(
            @RequestBody CreateShortcutRequest request
    ) {
        request.validate();
        Shortcut shortcut = shortcutService.create(request.getConnectedId(), request.getShortcutKeyword(), request.getProvidedActionId());
        return ResponseEntity
                .status(200)
                .header("location", "/a/shortcuts/" + shortcut.getShortcutId())
                .body(new ShortcutResponse(shortcut));
    }

    @PutMapping("/a/shortcuts")
    public ResponseEntity<Void> update() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/a/shortcuts")
    public ResponseEntity<Void> delete() {
        return ResponseEntity.noContent().build();
    }
}
