package com.something.my.app.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Entity
@ToString
@Table(name = "shortcut")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Shortcut {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "")
    @SequenceGenerator(name = "shortcutSeqGen", sequenceName = "SHORTCUT_SEQ_GEN", allocationSize = 25)
    private Long id;

    private String path;

    @ManyToOne(cascade = CascadeType.ALL)
    private App app;
}
