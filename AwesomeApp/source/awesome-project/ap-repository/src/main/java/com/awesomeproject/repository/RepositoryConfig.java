package com.awesomeproject.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.awesomeproject.domain")
@EnableJpaRepositories(basePackages = {"com.awesomeproject.repository"})
@EnableTransactionManagement
public class RepositoryConfig {
}
