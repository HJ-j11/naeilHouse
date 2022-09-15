package com.house.start.service;

import com.house.start.domain.entity.Member;
import com.house.start.domain.entity.Role;
import com.house.start.repository.MemberRepository;
import com.house.start.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SellerService {
    private final EntityManager em;
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    /**
     *  아이디로 한 명의 판매자 조회
     */
    public Member findSeller(Long id) {
        return memberRepository.findById(id).get();
    }

//    /**
//     * 전체 판매자 조회
//     */
public List<Member> findSellers() {
    Role role = roleRepository.findByRoleName("ROLE_SELLER");
    return memberRepository.findByUserRoles(role);
}

    /**
     * 판매자 승인
     */
    @Transactional
    public void approveSeller(Long seller_id) {
        Member member = memberRepository.findById(seller_id).get();
        member.setIsApproved(true);

    }

    /**
     * 판매자 승인 취소
     */
    @Transactional
    public void notapproveSeller(Long seller_id) {
        Member member = memberRepository.findById(seller_id).get();
        member.setIsApproved(false);
    }
}
