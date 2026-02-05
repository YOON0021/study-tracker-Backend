package com.studytracker.study_tracker.dto;

public record SignupRequest(
        String email,
        String password,
        String role // "USER" 또는 "LEADER"
) {}