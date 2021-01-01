package com.something.my.app.repository;

import com.something.my.app.domain.ConnectedApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectedAppRepository extends JpaRepository<ConnectedApp, Long> {
}
