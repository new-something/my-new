package com.something.my.app.repository;

import com.something.my.app.domain.ProvidedApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvidedAppRepository extends JpaRepository<ProvidedApp, Long> {
}
