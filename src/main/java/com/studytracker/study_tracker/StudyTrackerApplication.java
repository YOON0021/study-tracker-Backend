package com.studytracker.study_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // (선택) 생성일/수정일 자동 기록 기능 켜기
public class StudyTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyTrackerApplication.class, args);
    }

}