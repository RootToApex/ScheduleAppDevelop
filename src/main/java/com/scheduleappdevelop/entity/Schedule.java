package com.scheduleappdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor // JPA를 위한 기본 생성자
public class Schedule extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task;      // 할 일 내용

    // User 엔티티와 연관 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;    // 작성자 이름
    private String password;  // 비밀번호

    // 일정 생성 시 사용할 생성자
    public Schedule(String task, User user, String password) {
        this.task = task;
        this.user = user;
        this.password = password;
    }
    public void update(String task) {
        this.task = task;
    }
}