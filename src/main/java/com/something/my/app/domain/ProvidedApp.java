package com.something.my.app.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@ToString
@Table(name = "provided_app")
@EqualsAndHashCode(of = "appCode")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProvidedApp {
    @Id
    private Long appCode;
    private String appName;
    private String domain;

    @ManyToMany(mappedBy = "providedApps")
    private final List<AppTag> appTags = new ArrayList<>();
}
