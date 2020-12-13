package com.something.my.app.repository;


import com.something.my.app.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppRepository extends JpaRepository<App, Long> {

    List<App> findAppByUserId(Long userId);
}
