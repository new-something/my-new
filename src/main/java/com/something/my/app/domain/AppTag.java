package com.something.my.app.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
@Table(name = "app_tag")
@EqualsAndHashCode(of = "appTagId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AppTag {
    @Id
    private Long appTagId;

    private String tagName;

    @ManyToMany
    @JoinTable(
            name = "app_tagged",
            joinColumns = @JoinColumn(name = "app_tag_id"),
            inverseJoinColumns = @JoinColumn(name = "app_code")
    )
    private final List<ProvidedApp> providedApps = new ArrayList<>();
}
