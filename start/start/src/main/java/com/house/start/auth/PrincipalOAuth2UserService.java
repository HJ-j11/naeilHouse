package com.house.start.auth;

import com.house.start.domain.Member;
import com.house.start.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String register = userRequest.getClientRegistration().getRegistrationId(); // google
        String registerId = oAuth2User.getAttribute("sub");
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        String username = register + "_" + registerId;            // 사용자가 입력한 적은 없지만 만들어준다
        Member member = memberRepository.findByUsername(username);

        String email = oAuth2User.getAttribute("email");
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("" + uuid);

        if (member == null) {
            member = Member.OauthRegister()
                    .username(username)
                    .password(password).
                    email(email).build();

            memberRepository.save(member);
        }

        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }

}
