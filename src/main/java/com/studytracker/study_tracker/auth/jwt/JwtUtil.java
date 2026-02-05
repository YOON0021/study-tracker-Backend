package com.studytracker.study_tracker.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔑 비밀 키 (원래는 서버 설정파일에 숨겨야 하지만, 실습용이라 여기 적음)
    private static final String SECRET_KEY = "studytracker_secret_key_studytracker_secret_key";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10시간

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // 토큰 생성 (로그인 성공 시 호출)
    public String createToken(Long userId, String email) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId) // 토큰 안에 userId 숨김
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    // 1. 토큰에서 이메일(Subject) 꺼내기
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 2. 토큰이 유효한지 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // 위조되거나 만료된 토큰
        }
    }
}