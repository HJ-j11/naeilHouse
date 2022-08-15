package com.house.start.service.impl;

import com.house.start.domain.dto.Member.MemberDto;
import com.house.start.domain.entity.Member;
import com.house.start.domain.entity.Role;
import com.house.start.repository.MemberRepository;
import com.house.start.repository.RoleRepository;
import com.house.start.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(Member member){

        Role role = roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        member.setUserRoles(roles);
//        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    @Transactional
    public MemberDto getUser(Long id) {

        Member member = memberRepository.findById(id).orElse(new Member());
        ModelMapper modelMapper = new ModelMapper();
        MemberDto userDto = modelMapper.map(member, MemberDto.class);

        List<String> roles = member.getUserRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toList());

        userDto.setRoles(roles);
        return userDto;
    }

    @Transactional
    public List<Member> getUsers() {
        return memberRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        memberRepository.deleteById(id);
    }



    public Member findMemberByMId(String mId) {
        return memberRepository.findByUsername(mId);
    }

    public Member findMemberById(Long id) { return memberRepository.findMemberById(id); }
}
