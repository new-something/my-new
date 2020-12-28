package com.something.my.app.domain;

import com.something.my.user.domain.User;
import lombok.*;

import javax.persistence.*;


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

    @Enumerated(EnumType.STRING)
    @Column(name = "connect_type")
    private ConnectType connectType;

    @OneToOne
    @JoinColumn(name = "app_code")
    private ProvidedApp providedApp;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}


