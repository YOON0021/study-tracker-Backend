package com.studytracker.study_tracker.dto;

// 스터디 만들 때 "이름"을 받아오는 객체
public record StudyCreateRequest(
        String name
) {}