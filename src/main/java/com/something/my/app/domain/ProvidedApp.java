package com.something.my.app.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Getter
@ToString
@Entity(name = "ProvidedApp")
@Table(name = "provided_app")
@EqualsAndHashCode(of = "appCode")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProvidedApp {
    @Id
    private Long appCode;
    private String appName;
    private String appIcon;
    private String domain;
    private String description;

    @OneToMany(mappedBy = "providedApp")
    private final Set<ConnectedApp> connectedApp = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "providedApps")
    private final List<AppTag> appTags = new ArrayList<>();

    @OneToMany(mappedBy = "providedApp", cascade = CascadeType.ALL)
    private final Set<ProvidedAction> providedActions = new LinkedHashSet<>();

    public static ProvidedApp of(Long appCode) {
        ProvidedApp providedApp = new ProvidedApp();
        providedApp.appCode = appCode;
        return providedApp;
    }
}
