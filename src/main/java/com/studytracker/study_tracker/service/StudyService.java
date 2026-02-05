package com.studytracker.study_tracker.service;

import com.studytracker.study_tracker.domain.Study;
import com.studytracker.study_tracker.domain.User;
import com.studytracker.study_tracker.dto.RankingResponse; // (랭킹 DTO가 있다면 import, 없으면 제외)
import com.studytracker.study_tracker.repository.StudyRepository;
import com.studytracker.study_tracker.repository.SubmissionRepository;
import com.studytracker.study_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {

    private final StudyRepository studyRepository;
    private final SubmissionRepository submissionRepository; // 랭킹용
    private final UserRepository userRepository;         // 랭킹용

    // [1] 스터디 생성하기 (저장)
    @Transactional
    public Long createStudy(String name, Long leaderId) {
        Study study = new Study(name, leaderId);
        studyRepository.save(study);
        return study.getId(); // 만들어진 스터디 ID 반환 (예: 1)
    }

    // [2] 랭킹 조회하기 (아까 만든 기능 유지)
    public List<RankingResponse> getRanking(Long studyId) {
        List<Object[]> results = submissionRepository.getRankingByStudyId(studyId);
        List<RankingResponse> rankingList = new ArrayList<>();

        for (Object[] row : results) {
            Long userId = (Long) row[0];
            Long count = (Long) row[1];

            String username = userRepository.findById(userId)
                    .map(User::getEmail)
                    .orElse("알수없음");

            rankingList.add(new RankingResponse(username, count));
        }
        return rankingList;
    }
}