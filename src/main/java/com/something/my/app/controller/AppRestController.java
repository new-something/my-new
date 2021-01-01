package com.something.my.app.controller;

import com.something.my.app.controller.dto.ConnectAppRequest;
import com.something.my.app.domain.ConnectedApp;
import com.something.my.app.service.AppQueryService;
import com.something.my.app.service.ConnectedAppService;
import com.something.my.app.service.dto.ProvidedAppResponse;
import com.something.my.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.something.my.global.utils.Keys.SESSION;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AppRestController {

    private final AppQueryService appService;

    private final ConnectedAppService connectedAppService;

    @GetMapping("/apis/apps")
    public ResponseEntity<List<ProvidedAppResponse>> findApps(
            HttpServletRequest request,
            @RequestParam String tag
    ) {
        log.info(tag);
        User session = (User) request.getSession().getAttribute(SESSION);
        List<ProvidedAppResponse> apps = appService.findProvidedApp(session, tag);
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
        request.validate();
        User session = (User) req.getSession().getAttribute(SESSION);
        Long id = connectedAppService.connect(request.getAppCode(), session);
        return ResponseEntity.created(new URI("/apis/apps/" + id)).build();
    }

    @DeleteMapping("/apis/apps/{id}")
    public ResponseEntity<Void> disconnectApp(
            @PathVariable Long id
    ) {
        return ResponseEntity.noContent().build();
    }
}
