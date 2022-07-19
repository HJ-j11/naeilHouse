package com.house.start.repository;

import com.house.start.domain.Member;
import com.house.start.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);

    Member findMemberById(Long id);

    // ROLE로 Member 검색
    List<Member> findByRole(Role role);
}
