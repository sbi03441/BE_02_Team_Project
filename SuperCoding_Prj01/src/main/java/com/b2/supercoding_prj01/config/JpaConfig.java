package com.b2.supercoding_prj01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.b2.supercoding_prj01.repository.board","com.b2.supercoding_prj01.repository.comments","com.b2.supercoding_prj01.repository.user"},
        entityManagerFactoryRef = "entityManagerFactoryBean"
)

public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.b2.supercoding_prj01.repository.board","com.b2.supercoding_prj01.repository.comments","com.b2.supercoding_prj01.repository.user");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comment", "true");
        em.setJpaPropertyMap(properties);


        return em;
    }


}
