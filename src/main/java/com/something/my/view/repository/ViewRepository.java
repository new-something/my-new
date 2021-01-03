package com.something.my.view.repository;

import com.something.my.app.domain.ConnectedApp;
import com.something.my.app.domain.Shortcut;
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
        return em.createQuery("SELECT ca FROM ConnectedApp ca JOIN FETCH ca.user JOIN FETCH ca.providedApp pa WHERE ca.user = :user", ConnectedApp.class)
                .setParameter("user", user)
                .getResultList();
    }

    public List<Shortcut> findShortcutsByUser(User user) {
        return em.createQuery("SELECT s FROM Shortcut s JOIN FETCH s.app a JOIN FETCH a.user JOIN FETCH a.providedApp WHERE a.user = :user", Shortcut.class)
                .setParameter("user", user)
                .getResultList();
    }
}
