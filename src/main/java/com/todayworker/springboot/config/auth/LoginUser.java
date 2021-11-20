package com.todayworker.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 이 어노테이션이 생성될 수 있는 위치를 지정. PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용될 수 있다.
@Retention(RetentionPolicy.RUNTIME) // 커스텀 애노테이션 생성시 사용 (RUNTIME: 런타임 시점까지 유지)
public @interface LoginUser {
}
