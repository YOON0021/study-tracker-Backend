package com.studytracker.study_tracker.service;

import com.studytracker.study_tracker.domain.Submission;
import com.studytracker.study_tracker.dto.SubmissionRequest;
import com.studytracker.study_tracker.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final FineService fineService;

    public Long submitProblem(Long userId, SubmissionRequest request) {
        // 엔티티 생성
        Submission submission = new Submission(
                request.studyId(),
                userId,
                request.problemUrl()
        );
        // 저장
        submissionRepository.save(submission);
        return submission.getId();
    }
    public void gradeSubmission(Long submissionId, boolean isSuccess) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new IllegalArgumentException("제출 기록을 찾을 수 없습니다."));

        if (isSuccess) {
            submission.markAsSuccess(); // 성공 처리
        } else {
            // 🚨 실패 시 벌금 1,000원 부과 (금액은 나중에 정책으로 뺄 수 있음)
            fineService.imposeFine(
                    submission.getUserId(),
                    submission.getStudyId(),
                    1000,
                    "과제 인증 실패/거절"
            );
        }
    }
}