package com.todayworker.springboot.config;

import com.todayworker.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    // HandlerMethodArguementResolver는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가해야한다.
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> addArgumentResolvers) {
        addArgumentResolvers.add(loginUserArgumentResolver);
    }
}
