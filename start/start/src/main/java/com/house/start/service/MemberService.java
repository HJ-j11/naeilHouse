package com.house.start.service;

import com.house.start.domain.dto.Member.MemberDto;
import com.house.start.domain.entity.Member;

import java.util.List;

public interface MemberService {

    List<Member> getUsers();
    MemberDto getUser(Long id);
    void createUser(Member member);
    void deleteUser(Long idx);


}
