package com.something.my.app.controller;

import com.something.my.app.controller.dto.ConnectAppRequest;
import com.something.my.app.domain.ConnectedApp;
import com.something.my.app.service.AppQueryService;
import com.something.my.app.service.ConnectedAppService;
import com.something.my.app.service.dto.ConnectedAppResponse;
import com.something.my.app.service.dto.ProvidedAppResponse;
import com.something.my.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.something.my.global.utils.Keys.SESSION;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AppRestController {

    private final AppQueryService appService;

    private final ConnectedAppService connectedAppService;

    @GetMapping("/a/apps")
    public ResponseEntity<List<ProvidedAppResponse>> findApps(
            HttpServletRequest request,
            @RequestParam String tag
    ) {
        log.info(tag);
        User session = (User) request.getSession().getAttribute(SESSION);
        List<ProvidedAppResponse> apps = appService.findProvidedApp(session, tag);
        return ResponseEntity.ok(apps);
    }

    @GetMapping("/a/apps/{id}")
    public ResponseEntity<ConnectedApp> findOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/a/apps")
    public ResponseEntity<ConnectedAppResponse> connectApp(
            HttpServletRequest req,
            @RequestBody ConnectAppRequest request
    ) {
        request.validate();
        User session = (User) req.getSession().getAttribute(SESSION);
        ConnectedApp app = connectedAppService.connect(request.getAppCode(), session);
        return ResponseEntity
                .status(200)
                .header("Location", "/a/apps/" + app.getConnectedId())
                .body(new ConnectedAppResponse(app));
    }

    @DeleteMapping("/a/apps/{id}")
    public ResponseEntity<Void> disconnectApp(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        User session = (User) request.getSession().getAttribute(SESSION);
        connectedAppService.disconnect(id, session);
        return ResponseEntity.noContent().build();
    }
}
