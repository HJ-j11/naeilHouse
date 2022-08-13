package com.house.start.security.oauth;

import com.house.start.security.oauth.userInfo.GoogleOAuthUserInfo;
import com.house.start.security.oauth.userInfo.KakaoOAuthUserInfo;
import com.house.start.security.oauth.userInfo.NaverOAuthUserInfo;
import com.house.start.security.oauth.userInfo.OAuthUserInfo;

import java.util.Map;

public class OAuthUserInfoFactory {
    public static OAuthUserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOAuthUserInfo(attributes);
            case NAVER: return new NaverOAuthUserInfo(attributes);
            case KAKAO: return new KakaoOAuthUserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
