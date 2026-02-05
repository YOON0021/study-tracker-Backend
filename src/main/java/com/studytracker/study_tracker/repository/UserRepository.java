package com.studytracker.study_tracker.repository;

import com.studytracker.study_tracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 회원 찾기 (로그인할 때 필요)
    Optional<User> findByEmail(String email);

    // 이메일 중복 확인용
    boolean existsByEmail(String email);
}