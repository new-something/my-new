package com.something.my.app.service;

import com.something.my.app.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;
}
