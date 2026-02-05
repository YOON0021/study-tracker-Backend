package com.studytracker.study_tracker.dto;

public record SubmissionRequest(
        Long studyId,
        // Long userId,      // 일단 테스트를 위해 입력받음 (나중엔 토큰에서 추출)
        String problemUrl
) {}