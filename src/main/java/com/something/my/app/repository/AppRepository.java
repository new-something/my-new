package com.something.my.app.repository;


import com.something.my.app.domain.AppTagType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppRepository {

    private final EntityManager em;

    @SuppressWarnings("unchecked")
    public List<Object[]> findAllByTag(AppTagType tagType) {
        return em.createQuery("SELECT pa.appCode, pa.appName, pa.domain, pa.appIcon " +
                "FROM AppTag at JOIN at.providedApps pa WHERE at.tagName = :tagName")
                .setParameter("tagName", tagType)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findAll() {
        return em.createQuery("SELECT pa.appCode, pa.appName, pa.domain, pa.appIcon " +
                "FROM AppTag at JOIN at.providedApps pa GROUP BY pa.appCode")
                .getResultList();
    }

}
