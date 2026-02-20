# ✍️ Study-Tracker-BE

스터디 그룹을 생성하고 개인별 공부 시간을 추적하여 그룹 내 랭킹을 제공하는 서비스의 백엔드 시스템입니다.

## 

### 1. 기본 개요 및 구현
- **계층형 아키텍처** (Controller - Service - Repository)
- **JPA 연관관계** (단방향 매핑으로 복잡도 줄이기)
- **비즈니스 로직** (채점 → 벌금 부과 트랜잭션 처리)
- **보안** (Spring Security + JWT 커스텀 필터)
- **API 문서화** (Swagger)

### 2. 기술 스택
* **Framework:** Spring Boot 3.x
* **Database:** H2 Database (In-memory/File)
* **ORM:** Spring Data JPA
* **Security:** Spring Security, JSON Web Token (JWT)
* **Build Tool:** Gradle

### 3. 주요 기능
* **스터디 생성 (POST `/api/studies`):**
    * 현재 모든 요청에 대해 임시로 **ID 1번 유저**를 리더로 할당하여 생성됩니다.
    * DB 저장 로직 검증 완료 (성공 시 생성된 스터디 ID 반환).
* **회원가입/로그인 (`/api/auth/**`):**
    * 사용자 등록 및 인증 토큰 발급 로직이 구현되어 있습니다.

## 🛠️ 현재 구현 및 설정 상태

### 1. 보안 설정 (Spring Security & JWT)
* **접근 권한:** 현재 개발 및 테스트 편의를 위해 모든 API 엔드포인트에 대해 `permitAll()` 설정이 적용되어 있습니다.
* **H2 콘솔:** 데이터베이스 확인을 위해 `/h2-console` 접속이 허용되어 있으며, 프레임 옵션이 비활성화되어 있습니다.
* **JWT 상태:** `TokenProvider`와 `JwtFilter` 구현은 완료되었으나, 토큰 서명 불일치 이슈로 인해 현재는 보안 필터 체인에서 잠시 제외된 상태입니다.

## 🚀 실행 및 테스트 방법

1.  **애플리케이션 실행:** `StudyTrackerApplication.java` 실행
2.  **H2 콘솔 접속:** `http://localhost:8080/h2-console`
    * JDBC URL: `jdbc:h2:file:./study_db;MODE=MySQL`
3.  **API 문서(Swagger):** `http://localhost:8080/swagger-ui/index.html`

## 📌 향후 작업 계획
- [ ] 스터디 목록 조회 및 랭킹 데이터 반환 로직(Service/Repository) 정밀 점검
- [ ] JWT 서명 불일치(Signature Mismatch) 문제 해결 및 보안 필터 재적용
- [ ] 리액트(React) 프론트엔드 추가 구현과 데이터 바인딩 최적화
