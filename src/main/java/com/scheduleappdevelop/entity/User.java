package com.scheduleappdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user") // DB 테이블 이름을 user로 지정
@NoArgsConstructor
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; // 유저 이름

    @Column(nullable = false, unique = true)
    private String email; // 유저 이메일 (중복 방지)

    @Column(nullable = false)
    private String password;


    public User(String username, String email, String password) {

        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 최소 8글자 이상이어야 합니다.");
        }
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void update(String username, String email) {
        this.username = username;
        this.email = email;
    }
}