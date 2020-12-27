package com.something.my.app.repository;


import com.something.my.app.domain.ConnectedApp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppRepository {

    private final EntityManager em;

    public List<ConnectedApp> findByUserId(Long userId) {
        return em.createQuery("SELECT a FROM ConnectedApp a WHERE a.user.userId =:userId", ConnectedApp.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
