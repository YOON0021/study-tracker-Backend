package com.studytracker.study_tracker.controller;

import com.studytracker.study_tracker.dto.SubmissionRequest;
import com.studytracker.study_tracker.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import com.studytracker.study_tracker.repository.UserRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Long> submit(
            @RequestBody SubmissionRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Long currentUserId = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow().getId();

        Long submissionId = submissionService.submitProblem(currentUserId, request);

        return ResponseEntity.ok(submissionId);
    }
    // 채점 API: POST /api/submissions/{id}/grade?isSuccess=false
    @PostMapping("/{id}/grade")
    public ResponseEntity<String> grade(
            @PathVariable Long id,
            @RequestParam boolean isSuccess
    ) {
        submissionService.gradeSubmission(id, isSuccess);
        return ResponseEntity.ok("채점 완료: " + (isSuccess ? "성공" : "실패 (벌금 부과됨)"));
    }
}