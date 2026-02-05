package com.studytracker.study_tracker.dto;

import com.studytracker.study_tracker.domain.Member;

public class MemberDto {

    private Long id;
    private String name;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
