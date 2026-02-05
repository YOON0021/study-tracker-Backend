package com.studytracker.study_tracker.repository;

import com.studytracker.study_tracker.domain.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Long> {

    // 특정 유저의 벌금 내역 조회
    List<Fine> findByUserId(Long userId);

    // (핵심) 특정 유저의 총 벌금 합계 계산 (JPQL 사용)
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM Fine f WHERE f.userId = :userId AND f.studyId = :studyId")
    int sumFineByMember(@Param("userId") Long userId, @Param("studyId") Long studyId);
}