package com.youprice.onion.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class JPAQueryFactoryConfig {

	@Bean
	public JPAQueryFactory queryFactory(EntityManager entityManager) {
		return new JPAQueryFactory(entityManager);
	}

}
