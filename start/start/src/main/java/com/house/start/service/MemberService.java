package com.house.start.service;

import com.house.start.domain.Member;
import com.house.start.domain.Role;
import com.house.start.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public Member findMemberByMId(String mId) {
        return memberRepository.findByUsername(mId);
    }

    public Member findMemberById(Long id) { return memberRepository.findMemberById(id); }

    /*
     * 소비자 전체 목록 조회
     */
    public List<Member> findConsumers() {
        return memberRepository.findByRole(Role.CONSUMER);
    }

    /*
     * 판매자 전체 목록 조회
     */
    public List<Member> findSellers() {
        return memberRepository.findByRole(Role.SELLER);
    }


}
