package com.house.start.service;

import com.house.start.domain.*;
import com.house.start.domain.entity.Member;
import com.house.start.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    /**
     *  소비자 회원가입
     */
    @Transactional
    public void joinConsumer(Member member) {
        Cart cart = Cart.builder()
                .member(member)
                .build();

        cartRepository.save(cart);
        memberRepository.save(member);
    }

    @Transactional
    public void joinMember(Member member) {
        memberRepository.save(member);
    }

    public void checkDuplicate(Member member) {
        Member checkMember = memberRepository.findByUsername(member.getUsername());
        if(checkMember != null) {
            throw new IllegalStateException("이미 가입하였습니다.");
        }
    }
}
