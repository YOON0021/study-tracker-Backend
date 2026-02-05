package com.studytracker.study_tracker.service;

import com.studytracker.study_tracker.auth.jwt.JwtUtil;
import com.studytracker.study_tracker.domain.Role;
import com.studytracker.study_tracker.domain.User;
import com.studytracker.study_tracker.dto.LoginRequest;
import com.studytracker.study_tracker.dto.LoginResponse;
import com.studytracker.study_tracker.dto.SignupRequest;
import com.studytracker.study_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // SecurityConfig에서 등록한 암호화 기계
    private final JwtUtil jwtUtil; // 우리가 만든 토큰 발급기

    // 회원가입
    public Long signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        // 비밀번호 암호화! (절대 그냥 저장하면 안 됨)
        String encodedPassword = passwordEncoder.encode(request.password());

        // Role 변환 (String -> Enum)
        Role role = Role.valueOf(request.role().toUpperCase());

        User user = new User(request.email(), encodedPassword, role);
        userRepository.save(user);

        return user.getId();
    }

    // 로그인
    public LoginResponse login(LoginRequest request) {
        // 1. 이메일로 유저 찾기
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        // 2. 비밀번호 맞는지 확인 (암호화된 거랑 비교)
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // 3. 맞으면 토큰 발급
        String token = jwtUtil.createToken(user.getId(), user.getEmail());
        return new LoginResponse(token);
    }
}