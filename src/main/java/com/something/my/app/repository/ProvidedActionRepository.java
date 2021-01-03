package com.something.my.app.repository;

import com.something.my.app.domain.ProvidedAction;
import com.something.my.app.domain.ProvidedApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvidedActionRepository extends JpaRepository<ProvidedAction, Long> {

    List<ProvidedAction> findAllByProvidedApp(ProvidedApp providedApp);
}
