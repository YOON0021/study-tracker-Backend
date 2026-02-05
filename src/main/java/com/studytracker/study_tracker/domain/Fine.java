package com.studytracker.study_tracker.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Fine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;   // 벌금 낼 사람

    @Column(nullable = false)
    private Long studyId;  // 어느 스터디에서 발생했는지

    @Column(nullable = false)
    private int amount;    // 벌금 액수 (예: 1000)

    @Column(nullable = false)
    private String reason; // 벌금 사유 (예: "과제 미제출", "지각")

    private LocalDateTime imposedAt; // 벌금 부과된 시간

    // 생성자
    public Fine(Long userId, Long studyId, int amount, String reason) {
        this.userId = userId;
        this.studyId = studyId;
        this.amount = amount;
        this.reason = reason;
        this.imposedAt = LocalDateTime.now();
    }
}