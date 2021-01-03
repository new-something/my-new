package com.something.my.app.controller;

import com.something.my.app.service.ProvidedActionService;
import com.something.my.app.service.dto.ProvidedActionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProvidedActionRestController {

    private final ProvidedActionService providedActionService;

    @GetMapping("/apis/provided-actions")
    public ResponseEntity<List<ProvidedActionResponse>> findByAppCode(
            @RequestParam(name = "p_id") Long appCode
    )
    {
        return ResponseEntity.ok(providedActionService.findAllByAppCode(appCode));
    }
}
