package com.something.my.view.repository;

import com.something.my.app.domain.ConnectedApp;
import com.something.my.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ViewRepository {

    private final EntityManager em;

    public List<ConnectedApp> findConnectedAppsByUser(User user) {
        return em.createQuery("SELECT ca FROM ConnectedApp ca LEFT JOIN FETCH ca.shortcuts WHERE ca.user = :user", ConnectedApp.class)
                .setParameter("user", user)
                .getResultList();
    }
}
