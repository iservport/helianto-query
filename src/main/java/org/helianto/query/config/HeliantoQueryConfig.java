package org.helianto.query.config;

import org.helianto.query.data.jpa.QueryRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Query and Spring Data JPA configuration.
 * 
 * @author mauriciofernandesdecastro
 */
@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass=QueryRepositoryFactoryBean.class)
public class HeliantoQueryConfig {

}
