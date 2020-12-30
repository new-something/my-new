package com.something.my.app.domain;

import com.something.my.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Entity
@ToString
@Builder
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

    @ManyToOne
    @JoinColumn(name = "app_code")
    private ProvidedApp providedApp;

    @OneToMany(mappedBy = "app", cascade = CascadeType.ALL)
    private final Set<Shortcut> shortcuts = new LinkedHashSet<>();

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
}


