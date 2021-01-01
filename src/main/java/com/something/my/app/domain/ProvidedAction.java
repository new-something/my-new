package com.something.my.app.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@Table(name = "provided_action")
@Entity(name = "ProvidedAction")
@EqualsAndHashCode(of = "providedActionId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProvidedAction {

    @Id
    private Long providedActionId;

    @Enumerated(EnumType.STRING)
    private ProvidedActionType type;

    private String url;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_code")
    private ProvidedApp providedApp;
}