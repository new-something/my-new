package com.something.my.app.repository;


import com.something.my.app.domain.Shortcut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortcutRepository extends JpaRepository<Shortcut, Long> {
}
