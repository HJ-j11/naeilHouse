package com.house.start.service;

import com.house.start.domain.Member;
import com.house.start.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SellerService {
    private final EntityManager em;
    private final MemberRepository memberRepository;

    /**
     *  아이디로 한 명의 판매자 조회
     */
    public Member findSeller(Long id) {
        return memberRepository.findById(id).get();
    }


    /**
     * 판매자 승인
     */
    public void approveSeller(Long seller_id) throws Exception {
        Member member = memberRepository.findById(seller_id)
                .orElseThrow(() -> new Exception("판매자 정보에 문제가 있습니다."));
        member.setIsApproved(true);

    }

    /**
     * 판매자 승인 취소
     */
    public void notapproveSeller(Long seller_id) throws Exception {
        Member member = memberRepository.findById(seller_id)
                .orElseThrow(() -> new Exception("판매자 정보에 문제가 있습니다."));
        member.setIsApproved(false);
    }
}
