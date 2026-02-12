package com.scheduleappdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor // JPA를 위한 기본 생성자
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task;      // 할 일 내용
    private String author;    // 작성자 이름
    private String password;  // 비밀번호

    // 일정 생성 시 사용할 생성자
    public Schedule(String task, String author, String password) {
        this.task = task;
        this.author = author;
        this.password = password;
    }
    public void update(String task, String author) {
        this.task = task;
        this.author = author;
    }
}