package com.studytracker.study_tracker.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Submission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studyId;  // Study 엔티티의 ID

    @Column(nullable = false)
    private Long userId;   // User 엔티티의 ID

    @Column(nullable = false)
    private String problemUrl; // 문제 링크

    private LocalDateTime submittedAt; // 제출 시간

    private boolean isSuccess; // 성공 여부 (기본 false)

    // 생성자
    public Submission(Long studyId, Long userId, String problemUrl) {
        this.studyId = studyId;
        this.userId = userId;
        this.problemUrl = problemUrl;
        this.submittedAt = LocalDateTime.now(); // 생성 시점 시간 고정
        this.isSuccess = false;
    }

    // 추후 성공 처리용 메서드
    public void markAsSuccess() {
        this.isSuccess = true;
    }
}