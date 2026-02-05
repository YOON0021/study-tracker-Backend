package com.studytracker.study_tracker.repository;

import com.studytracker.study_tracker.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
    // 기본 기능(저장, 조회)은 자동으로 만들어집니다.
}