package com.something.my.app.repository;

import com.something.my.app.domain.UrlRedirection;
import com.something.my.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRedirectionRepository extends JpaRepository<UrlRedirection, Long> {

    List<UrlRedirection> findAllByUser(User user);
}
