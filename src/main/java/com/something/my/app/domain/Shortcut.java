package com.something.my.app.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Builder
@ToString
@Entity(name = "Shortcut")
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

    private String destinationUrl;

    @Enumerated(EnumType.STRING)
    private ShortcutType type;

    @JoinColumn(name = "connected_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ConnectedApp app;
}
