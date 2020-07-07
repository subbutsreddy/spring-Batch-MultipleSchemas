package com.profinch.finflowz.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "accountEntityManagerFactory", transactionManagerRef = "accountTransactionManager", basePackages = {
		"com.profinch.finflowz.account.repository" })
public class AccountDBConfig {

	@Bean(name = "accountDataSource")
	@ConfigurationProperties(prefix = "spring.account.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "accountEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("accountDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.profinch.finflowz.model.account").persistenceUnit("Account").build();
	}

	@Bean(name = "accountTransactionManager")
	public PlatformTransactionManager accountTransactionManager(
			@Qualifier("accountEntityManagerFactory") EntityManagerFactory accountEntityManagerFactory) {
		return new JpaTransactionManager(accountEntityManagerFactory);
	}
}
