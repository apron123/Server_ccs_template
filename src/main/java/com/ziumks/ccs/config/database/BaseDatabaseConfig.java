package com.ziumks.ccs.config.database;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
@EnableJpaRepositories(
        basePackages = {DatabaseConstants.BaseDatabase.package_domain, DatabaseConstants.BaseDatabase.package_repository},
        entityManagerFactoryRef = DatabaseConstants.BaseDatabase.entity_manager_factory,
        transactionManagerRef = DatabaseConstants.BaseDatabase.tx_manager
)
@RequiredArgsConstructor
public class BaseDatabaseConfig {

    private final DatabaseProperties dbProps;

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DatabaseProperties.Base baseInfo = dbProps.getBase();

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(baseInfo.getDriver());
        dataSource.setJdbcUrl(baseInfo.getUrl());
        dataSource.setUsername(baseInfo.getUsername());
        dataSource.setPassword(baseInfo.getPassword());
        dataSource.setMaximumPoolSize(Integer.parseInt(baseInfo.getMaxPoolSize()));

        log.info("#################################################");
        log.info("## Database Connection - Base Config Database ");
        log.info("Driver      : {}", baseInfo.getDriver());
        log.info("URL         : {}", baseInfo.getUrl());
        log.info("USERNAME    : {}", baseInfo.getUsername());
        log.info("PASSWORD    : {}", baseInfo.getPassword());
        log.info("MaxPoolSize : {}", baseInfo.getMaxPoolSize());
        log.info("#################################################");

        return dataSource;
    }

    @Primary
    @Bean(name=DatabaseConstants.BaseDatabase.tx_manager)
    @Autowired
    public JpaTransactionManager jpaTransactionManager(@Qualifier(value = DatabaseConstants.BaseDatabase.entity_manager_factory) LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }

    @Primary
    @Bean(name = DatabaseConstants.BaseDatabase.entity_manager_factory)
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        DatabaseProperties.Base baseInfo = dbProps.getBase();

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(false);
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(DatabaseConstants.BaseDatabase.package_domain);

        Properties props = new Properties();
        props.setProperty(AvailableSettings.DIALECT, baseInfo.getDialect());
        props.setProperty(AvailableSettings.SHOW_SQL, baseInfo.getShowSql());
        props.setProperty(AvailableSettings.FORMAT_SQL, "true");
        props.setProperty(AvailableSettings.HBM2DDL_AUTO, baseInfo.getDdlAuto());

        if(!StringUtils.isEmpty(baseInfo.getSchema())){
            props.setProperty(AvailableSettings.DEFAULT_SCHEMA, baseInfo.getSchema());
        }

        entityManagerFactoryBean.setJpaProperties(props);
        return entityManagerFactoryBean;
    }
}

