package com.studytracker.study_tracker.dto;

public record RankingResponse(
        String username, // 유저 이름 (또는 이메일)
        long score       // 성공한 문제 수 (또는 벌금 총액)
) {}