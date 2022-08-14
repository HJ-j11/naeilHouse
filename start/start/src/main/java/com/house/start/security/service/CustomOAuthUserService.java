package com.house.start.security.service;

import com.house.start.domain.entity.Member;
import com.house.start.domain.entity.Role;
import com.house.start.repository.MemberRepository;
import com.house.start.repository.RoleRepository;
import com.house.start.security.listener.SetupDataLoader;
import com.house.start.security.oauth.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomOAuthUserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 로그인 진행 서비스 구분 코드
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName(); // 로그인 진행 필드 값 - 구글 "sub"

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Member member = saveOAuth(attributes);

        Set<String> userRoles = member.getUserRoles()
                .stream()
                .map(userRole -> userRole.getRoleName())
                .collect(Collectors.toSet());

        List<GrantedAuthority> collect = userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new DefaultOAuth2User(
                collect,
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    };

    @Transactional
    public Member saveOAuth(OAuthAttributes oAuthAttributes) {
        String name = oAuthAttributes.getName();
        String email = oAuthAttributes.getEmail();

        Set<Role> userRoles = new HashSet<>();
        Role role = roleRepository.findByRoleName("ROLE_CONSUMER");
        userRoles.add(role);

        Member member = Member.builder()
                .name(name)
                .username(email)
                .userRoles(userRoles)
                .build();

        return memberRepository.save(member);
    }


}
