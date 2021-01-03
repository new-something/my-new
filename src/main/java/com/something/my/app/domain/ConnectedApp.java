package com.something.my.app.domain;

import com.something.my.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Builder
@ToString
@Entity(name = "ConnectedApp")
@Table(name = "connected_app")
@EqualsAndHashCode(of = "connectedId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ConnectedApp {

    @Id
    @Column(name = "connected_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "connectedAppIdSeqGen")
    @SequenceGenerator(name = "connectedAppIdSeqGen", sequenceName = "CONNECTED_APP_ID_SEQ_GEN", allocationSize = 20)
    private Long connectedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_code")
    private ProvidedApp providedApp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "app")
    private final Set<Shortcut> shortcuts = new LinkedHashSet<>();
}


