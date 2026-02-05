package com.studytracker.study_tracker.controller;

import com.studytracker.study_tracker.domain.User;
import com.studytracker.study_tracker.dto.RankingResponse;
import com.studytracker.study_tracker.dto.StudyCreateRequest;
import com.studytracker.study_tracker.repository.UserRepository;
import com.studytracker.study_tracker.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studies")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final UserRepository userRepository;

    /**
     * [1] 스터디 생성 API
     * POST /api/studies
     */
    @PostMapping
    public ResponseEntity<Long> createStudy(@RequestBody StudyCreateRequest request) {
        // ⚠️ 보안을 풀었으므로 토큰에서 유저를 찾지 않고 임시로 1번 유저를 할당합니다.
        Long tempUserId = 1L;

        Long studyId = studyService.createStudy(request.name(), tempUserId);
        return ResponseEntity.ok(studyId);
    }

    /**
     * [2] 스터디 랭킹 조회 API
     * GET /api/studies/{studyId}/ranking
     */
    @GetMapping("/{studyId}/ranking")
    public ResponseEntity<List<RankingResponse>> getRanking(@PathVariable Long studyId) {
        List<RankingResponse> ranking = studyService.getRanking(studyId);
        return ResponseEntity.ok(ranking);
    }
}