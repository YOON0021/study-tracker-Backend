package com.studytracker.study_tracker.repository;

import com.studytracker.study_tracker.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
