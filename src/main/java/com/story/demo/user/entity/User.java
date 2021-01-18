package com.story.demo.user.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "[user]")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
