package com.scheduleappdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user") // DB 테이블 이름을 user로 지정
@NoArgsConstructor
public class User extends Timestamped { // 유저도 생성/수정일이 있으면 좋으니까!

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; // 유저 이름

    @Column(nullable = false, unique = true)
    private String email; // 유저 이메일 (중복 방지)

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void update(String username, String email) {
        this.username = username;
        this.email = email;
    }
}