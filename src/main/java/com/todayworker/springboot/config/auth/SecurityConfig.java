package com.todayworker.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // 스프링 시큐리티 설정들을 활성화시켜 준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] SWAGGER_AUTH_WHITELIST = {
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/v2/api-docs",
        "/webjars/**"
    };


    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers(SWAGGER_AUTH_WHITELIST);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // h2-console 화면을 사용하기위해 해당 옵션 disable
            .headers().frameOptions().disable()// h2-console 화면을 사용하기위해 해당 옵션 disable
            .and()
            .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점. authorizeRequests가 선언되어야만 antMathcers 옵션을 사용 할 수 있다
            .antMatchers("/", "/css/**", "/images/**", "/js/**")
            .permitAll()  // 권한 관리 대상을 지정하는 옵션. URL, HTTP 메소드별로 관리가능
//                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // "/" 등 지정된 URL들을 permitAll() 옵션을 통해 전체 열람 권한을 주고 "/api/v1/**"는 USER 권한 가진 사람만 가능
//                .anyRequest().authenticated() // anyRequest->설정된 값들 이외 나머지 URL들을 나타낸다. 여기서는 authenticated()을 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용. 인증된 사용자 즉, 로그인한 사용자들을 이야기한다.
            .and()
            .logout() // 로그아웃 기능에 대한 여러 설정의 진입점
            .logoutSuccessUrl("http://localhost:3333") // 로그아웃 성공 시 / 주소로 이동한다.
            .and()
            .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점입니다.
            .defaultSuccessUrl("http://localhost:3333")
            .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당한다.
            .userService(
                customOAuth2UserService); // 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스를 구현체로 등록. 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.

    }
}
