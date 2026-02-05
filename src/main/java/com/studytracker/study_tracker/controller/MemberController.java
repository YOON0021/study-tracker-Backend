package com.studytracker.study_tracker.controller;

import com.studytracker.study_tracker.domain.Study;
import com.studytracker.study_tracker.dto.MemberDto;
import com.studytracker.study_tracker.domain.Member;
import com.studytracker.study_tracker.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String createMember(@RequestParam String name) {
        Long id = memberService.join(name);
        return "member id = " + id;
    }

    @GetMapping("/list")
    public List<MemberDto> list() {
        return memberService.findMembers()
                .stream()
                .map(MemberDto::new)
                .collect(Collectors.toList());
    }
}
