package com.something.my.app.controller;

import com.something.my.app.controller.dto.ConnectAppRequest;
import com.something.my.app.domain.ConnectedApp;
import com.something.my.app.repository.AppRepository;
import com.something.my.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.something.my.global.utils.Keys.SESSION;

@RestController
@RequiredArgsConstructor
public class AppRestController {

    private final AppRepository appRepository;

    @GetMapping("/apis/apps")
    public ResponseEntity<List<ConnectedApp>> findApps(
            HttpServletRequest request
    ) {
        User session = (User) request.getSession().getAttribute(SESSION);
        List<ConnectedApp> apps = appRepository.findByUserId(session.getUserId());
        return ResponseEntity.ok(apps);
    }

    @GetMapping("/apis/apps/{id}")
    public ResponseEntity<ConnectedApp> findOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/apis/apps")
    public ResponseEntity<Void> connectApp(
            HttpServletRequest req,
            @RequestBody ConnectAppRequest request
    ) throws URISyntaxException {
        User session = (User) req.getSession().getAttribute(SESSION);
        return ResponseEntity.created(new URI("/apis/apps/" + 1)).build();
    }

    @DeleteMapping("/apis/apps/{id}")
    public ResponseEntity<Void> disconnectApp(
            @PathVariable Long id
    ) {
        return ResponseEntity.noContent().build();
    }
}
