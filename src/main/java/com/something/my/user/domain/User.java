package com.something.my.user.domain;

import com.something.my.app.domain.ConnectedApp;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Entity
@ToString
@Table(name = "user")
@Builder
@EqualsAndHashCode(of = "userId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Serializable {

    public static final User EMPTY = new User();

    @Id
    private Long userId;

    private String userName;

    private String email;

    private String name;

    @OneToMany(mappedBy = "user")
    private final Set<ConnectedApp> connectedApps = new LinkedHashSet<>();
}
