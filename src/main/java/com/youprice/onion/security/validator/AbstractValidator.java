package com.youprice.onion.security.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//중복 검사 유효성 검증을 위해 Validator를 구현한 추상클래스
@Slf4j
public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @SuppressWarnings("unchecked") //컴파일러에서 경고하지 않기 위해 사용
    @Override
    public void validate(Object target, Errors errors) {
        try {
            doValidate((T) target, errors);
        } catch (IllegalStateException e) {
            log.error("중복 검증 에러", e);
            throw e;
        }
    }

    //유효성 검증 로직
    protected abstract void doValidate(final T memberDTO, final Errors errors);
}
