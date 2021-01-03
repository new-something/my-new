package com.something.my.app.repository;

import com.something.my.app.domain.ConnectedApp;
import com.something.my.app.domain.ProvidedApp;
import com.something.my.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConnectedAppRepository extends JpaRepository<ConnectedApp, Long> {

    Optional<ConnectedApp> findByProvidedAppAndUser(ProvidedApp providedApp, User user);
}
