package com.house.start.repository;

import com.house.start.domain.entity.Member;
import com.house.start.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);

    Member findMemberById(Long id);

    int countByUsername(String username);

    @Override
    void delete(Member member);

    List<Member> findByUserRoles(Role role);
}
