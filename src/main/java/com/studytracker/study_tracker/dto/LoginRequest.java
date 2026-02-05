package com.studytracker.study_tracker.dto;

public record LoginRequest(
        String email,
        String password
) {}