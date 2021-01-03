package com.something.my.app.controller;

import com.something.my.app.service.ShortcutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class ShortcutRestController {

    private final ShortcutService shortcutService;

    @GetMapping("/apis/shortcuts")
    public ResponseEntity<?> findAllByAppId(
            @RequestParam(name = "p_id") Long appCode
    ){

        return ResponseEntity.ok().build();
    }

    @GetMapping("/apis/shortcuts/{id}")
    public ResponseEntity<?> findById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/apis/shortcuts")
    public ResponseEntity<Void> create() throws URISyntaxException {
        return ResponseEntity.created(new URI("/apis/shortcuts/")).build();
    }

    @PutMapping("/apis/shortcuts")
    public ResponseEntity<Void> update() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/apis/shortcuts")
    public ResponseEntity<Void> delete() {
        return ResponseEntity.noContent().build();
    }
}
