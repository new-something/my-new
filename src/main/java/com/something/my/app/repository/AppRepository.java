package com.something.my.app.repository;


import com.something.my.app.domain.AppTagType;
import com.something.my.app.domain.ConnectedApp;
import com.something.my.app.domain.ProvidedApp;
import com.something.my.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppRepository {

    private final EntityManager em;

    public List<ProvidedApp> findAllProvidedAppsByTag(AppTagType tagType) {
        return em.createQuery("SELECT pa FROM ProvidedApp pa JOIN pa.appTags at JOIN FETCH pa.providedActions WHERE at.tagName = :tagName", ProvidedApp.class)
                .setParameter("tagName", tagType)
                .getResultList();
    }

    public List<ProvidedApp> findAllProvidedApps() {
        return em.createQuery("SELECT pa FROM ProvidedApp pa JOIN FETCH pa.providedActions", ProvidedApp.class).getResultList();
    }

    public List<ConnectedApp> findConnectedAppsByUser(User user) {
        return em.createQuery("SELECT ca FROM ConnectedApp ca JOIN FETCH ca.providedApp WHERE ca.user = :user", ConnectedApp.class)
                .setParameter("user", user)
                .getResultList();
    }

    public List<ConnectedApp> findConnectedAppsByUserAndTag(User user, AppTagType tagType) {
        return em.createQuery("SELECT ca FROM ConnectedApp ca JOIN FETCH ca.providedApp JOIN ca.providedApp.appTags at WHERE ca.user = :user AND at.tagName = :tagName", ConnectedApp.class)
                .setParameter("user", user)
                .setParameter("tagName", tagType)
                .getResultList();
    }

}
