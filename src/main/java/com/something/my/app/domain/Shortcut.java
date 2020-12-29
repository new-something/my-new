package com.something.my.app.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Entity
@ToString
@Table(name = "shortcut")
@EqualsAndHashCode(of = "shortcutId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Shortcut {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shortcutSeqGen")
    @SequenceGenerator(name = "shortcutSeqGen", sequenceName = "SHORTCUT_SEQ_GEN", allocationSize = 25)
    private Long shortcutId;

    private String path;

    private String targetUrl;

    @JoinColumn(name = "connected_id")
    @ManyToOne
    private ConnectedApp app;
}
