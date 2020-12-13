package com.something.my.app.domain;

import com.something.my.user.domain.User;
import lombok.*;

import javax.persistence.*;


@Getter
@Entity
@ToString
@Builder
@Table(name = "app")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class App {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appIdSeqGen")
    @SequenceGenerator(name = "appIdSeqGen", sequenceName = "APP_ID_SEQ_GEN", allocationSize = 20)
    private Long id;

    @Column(name = "app_name")
    private String appName;

    @Enumerated(EnumType.STRING)
    @Column(name = "app_type")
    private AppType appType;

    @Enumerated(EnumType.STRING)
    @Column(name = "connect_type")
    private ConnectType connectType;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
