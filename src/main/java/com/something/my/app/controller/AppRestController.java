package com.something.my.app.controller;

import com.something.my.app.controller.dto.ConnectAppRequest;
import com.something.my.app.domain.App;
import com.something.my.app.domain.AppType;
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
    public ResponseEntity<List<App>> findApps(
            HttpServletRequest request
    ) {
        User session = (User) request.getSession().getAttribute(SESSION);
        List<App> apps = appRepository.findAppByUserId(session.getId());
        return ResponseEntity.ok(apps);
    }

    @GetMapping("/apis/apps/{id}")
    public ResponseEntity<App> findOne(
            @PathVariable Long id
    ) {
        App app = appRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(app);
    }

    @PostMapping("/apis/apps")
    public ResponseEntity<Void> connectApp(
            HttpServletRequest req,
            @RequestBody ConnectAppRequest request
    ) throws URISyntaxException {
        User session = (User) req.getSession().getAttribute(SESSION);
        AppType appType = AppType.valueOf(request.getAppType());
        App app = App.builder()
                .appName(request.getAppName())
                .appType(appType)
                .user(session)
                .build();
        App save = appRepository.save(app);
        return ResponseEntity.created(new URI("/apis/apps/" + save.getId())).build();
    }

    @DeleteMapping("/apis/apps/{id}")
    public ResponseEntity<Void> disconnectApp(
            @PathVariable Long id
    ) {
        App app = App.builder().id(id).build();
        appRepository.delete(app);
        return ResponseEntity.noContent().build();
    }
}
