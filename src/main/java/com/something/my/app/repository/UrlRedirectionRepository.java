package com.something.my.app.repository;

import com.something.my.app.domain.UrlRedirection;
import com.something.my.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UrlRedirectionRepository extends JpaRepository<UrlRedirection, Long> {

    @Query("SELECT u FROM UrlRedirection u JOIN FETCH u.user WHERE u.user = :user")
    List<UrlRedirection> findAllByUser(User user);
}
