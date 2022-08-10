package com.house.start.security.listener;

import com.house.start.domain.entity.Member;
import com.house.start.domain.entity.Resources;
import com.house.start.domain.entity.Role;
import com.house.start.repository.MemberRepository;
import com.house.start.repository.ResourcesRepository;
import com.house.start.repository.RoleRepository;
import com.house.start.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        try {
            setupSecurityResources();
        } catch (Exception e) {
            e.printStackTrace();
        }

        alreadySetup = true;
    }

    /**
     *  초기 데이터 (권한, 자원, 사용자) 삽입
     */
    private void setupSecurityResources() throws Exception {

        // 관리자
        Set<Role> roles = new HashSet<>();
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자");
        roles.add(adminRole);

        createResourceIfNotFound("/sellers/**", "", roles, "url");
        createResourceIfNotFound("/consumers", "", roles, "url");
        createResourceIfNotFound("/posts", "", roles, "url");
        createResourceIfNotFound("/items", "", roles, "url");
        createResourceIfNotFound("/orders", "", roles, "url");
        createUserIfNotFound("관리자", "admin", "pass", roles);

        // 판매자
        Set<Role> roles2 = new HashSet<>();
        Role sellerRole = createRoleIfNotFound("ROLE_SELLER", "판매자");
        roles2.add(sellerRole);

        createResourceIfNotFound("/seller/**", "", roles2, "url");
        createUserIfNotFound("판매자", "seller", "pass", roles2);
//        createRoleHierarchyIfNotFound(sellerRole, adminRole);

        // 소비자
        Set<Role> roles3 = new HashSet<>();
        Role consumerRole = createRoleIfNotFound("ROLE_CONSUMER", "소비자");
        roles3.add(consumerRole);

        createUserIfNotFound("소비자", "consumer", "pass", roles3);
//        createRoleHierarchyIfNotFound(consumerRole, sellerRole);

        urlFilterInvocationSecurityMetadataSource.reload();

    }

    @Transactional
    public Role createRoleIfNotFound(String roleName, String roleDesc) {

        Role role = roleRepository.findByRoleName(roleName);

        if (role == null) {
            role = Role.builder()
                    .roleName(roleName)
                    .roleDesc(roleDesc)
                    .build();
        }
        return roleRepository.save(role);
    }

    @Transactional
    public Member createUserIfNotFound(String name, String userName, String password, Set<Role> roleSet) {

        Member account = memberRepository.findByUsername(userName);

        if (account == null) {
            account = Member.builder()
                    .name(name)
                    .username(userName)
                    .password(passwordEncoder.encode(password))
                    .userRoles(roleSet)
                    .build();
        }
        return memberRepository.save(account);
    }

    @Transactional
    public Resources createResourceIfNotFound(String resourceName, String httpMethod, Set<Role> roleSet, String resourceType) {
        Resources resources = resourcesRepository.findByResourceNameAndHttpMethod(resourceName, httpMethod);

        if (resources == null) {
            resources = Resources.builder()
                    .resourceName(resourceName)
                    .roleSet(roleSet)
                    .httpMethod(httpMethod)
                    .resourceType(resourceType)
                    .orderNum(count.incrementAndGet())
                    .build();
        }
        return resourcesRepository.save(resources);
    }

}