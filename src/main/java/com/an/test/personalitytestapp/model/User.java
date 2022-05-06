package com.an.test.personalitytestapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_seq",
            strategy = GenerationType.SEQUENCE)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String userName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
