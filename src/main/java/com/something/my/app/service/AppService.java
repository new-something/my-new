package com.something.my.app.service;

import com.something.my.app.domain.ProvidedApp;
import com.something.my.app.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;

    public List<ProvidedApp> findProvidedApp(){
        return null;
    }
}
