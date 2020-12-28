package com.something.my.app.service;

import com.something.my.app.domain.AppTagType;
import com.something.my.app.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.something.my.app.domain.AppTagType.ALL;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;

    public List<Object[]> findProvidedApp(String tagName){
        AppTagType appTagType = AppTagType.valueOf(tagName);
        if (appTagType == ALL) {
            return appRepository.findAll();
        }

        return appRepository.findAllByTag(appTagType);
    }
}
