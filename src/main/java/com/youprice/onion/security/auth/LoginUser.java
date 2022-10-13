package com.youprice.onion.security.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//세션 관련 중복 코드를 제거하기 위해 LoginUser 어노테이션을 쓰기 위함
@Target(ElementType.PARAMETER) // 파라미터로 선언된 객체만 사용
@Retention(RetentionPolicy.RUNTIME) //런타임까지 남아있는다.
public @interface LoginUser {
}
