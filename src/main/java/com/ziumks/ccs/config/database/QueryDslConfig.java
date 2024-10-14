package com.ziumks.ccs.config.database;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ziumks.ccs.config.database.DatabaseConstants.BaseDatabase.*;

@Configuration
@RequiredArgsConstructor
public class QueryDslConfig {
    @PersistenceContext(unitName = entity_manager_factory)
    private EntityManager baseEntityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(baseEntityManager);
    }

}
