package com.something.my.app.domain;

import com.something.my.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@ToString
@Entity(name = "UrlRedirection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UrlRedirection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "")
    @SequenceGenerator(name = "urlRedirectionSeqGen", sequenceName = "URL_REDIRECT_SEQ_GEN", allocationSize = 25)
    private Long urlRedirectionId;

    private String path;

    private String destinationUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
