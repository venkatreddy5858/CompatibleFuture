package com.completableFuture.manager;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.Data;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "employeeEntityManagerFactory", transactionManagerRef = "employeeTransactionManager", basePackages = {
		"com.completableFuture.dao" })
public class EntityManager {

    @Value("${spring.datasource.url}")
    private String url;
    
    @Value("${spring.datasource.username}")
    private String username;
    
    @Value("${spring.datasource.password}")
    private String password;
    
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassNae;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public String getDriverClassNae() {
		return driverClassNae;
	}

	public void setDriverClassNae(String driverClassNae) {
		this.driverClassNae = driverClassNae;
	}

	@Bean("employeeDataSource")
	@Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassNae);
        return dataSource;
    }
    
	@Primary
	@Bean(name = "employeeEntityManagerFactory")
	@PersistenceContext(unitName ="employee" )
	public LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("employeeDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource())
				.packages("com.completableFuture.model").persistenceUnit("employee").build();
	}

	@Bean(name = "employeeTransactionManager")
	public PlatformTransactionManager employeeTransactionManager(
			@Qualifier("employeeEntityManagerFactory") EntityManagerFactory employeeEntityManagerFactory) {
		return new JpaTransactionManager(employeeEntityManagerFactory);
	}
}