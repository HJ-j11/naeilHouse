package com.house.start.security.oauth;

import com.house.start.domain.entity.Member;
import com.house.start.repository.MemberRepository;
import com.house.start.security.oauth.userInfo.OAuthUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomOAuthUserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        try {
            return this.processOAuth2User(userRequest, oAuth2User);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuthUserInfo userInfo = OAuthUserInfoFactory.getOAuth2UserInfo(providerType, oAuth2User.getAttributes());
        Member member = memberRepository.findByUsername(userInfo.getId());

        if(member != null) {
            if(providerType != member.getProviderType()) {
                throw new OAuth2AuthenticationException("Looks like you're signed up with " +
                        member.getProviderType() + " account. Please use your " + member.getProviderType() +
                        " account to login.");
            }

        }

        return null;
    }


}
