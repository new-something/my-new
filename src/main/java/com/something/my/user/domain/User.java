package com.something.my.user.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Entity
@ToString
@Table(name = "user")
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User implements Serializable {

    public static final User EMPTY = new User();

    @Id
    private Long id;

    private String userName;

    private String email;

    private String name;
}
