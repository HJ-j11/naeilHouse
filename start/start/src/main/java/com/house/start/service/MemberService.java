package com.house.start.service;

import com.house.start.domain.Member;
import com.house.start.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public Member findMemberByMId(String mId) {
        return memberRepository.findByUsername(mId);
    }

    public Member findMemberById(Long id) { return memberRepository.findMemberById(id); }



}
