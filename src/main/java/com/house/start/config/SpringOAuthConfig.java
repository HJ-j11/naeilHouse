package com.house.start.config;


import com.house.start.security.service.CustomOAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(1)
public class SpringOAuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomOAuthUserService customOAuthUserService;

    @Override
    protected  void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                .userService(customOAuthUserService);
    }
}
