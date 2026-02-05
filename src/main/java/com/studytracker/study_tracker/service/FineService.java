package com.studytracker.study_tracker.service;

import com.studytracker.study_tracker.domain.Fine;
import com.studytracker.study_tracker.repository.FineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FineService {

    private final FineRepository fineRepository;

    // 벌금 부과하기
    public void imposeFine(Long userId, Long studyId, int amount, String reason) {
        Fine fine = new Fine(userId, studyId, amount, reason);
        fineRepository.save(fine);
    }

    // 현재까지 쌓인 벌금 조회 (읽기 전용)
    @Transactional(readOnly = true)
    public int getTotalFine(Long userId, Long studyId) {
        return fineRepository.sumFineByMember(userId, studyId);
    }
}