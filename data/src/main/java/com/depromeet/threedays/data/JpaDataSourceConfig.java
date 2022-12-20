package com.depromeet.threedays.data;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = JpaDataSourceConfig.BASE_PACKAGE,
		transactionManagerRef = JpaDataSourceConfig.TRANSACTION_MANAGER_NAME,
		entityManagerFactoryRef = JpaDataSourceConfig.ENTITY_MANAGER_FACTORY_NAME)
public class JpaDataSourceConfig {

	public static final String INTEGRATION_DATA_SOURCE_NAME = "integrationDataSource";
	private static final String SERVICE_NAME = "threedays";
	public static final String BASE_PACKAGE = "com.depromeet." + SERVICE_NAME;
	public static final String ENTITY_MANAGER_FACTORY_NAME =
			SERVICE_NAME + "DomainEntityManagerFactory";
	public static final String TRANSACTION_MANAGER_NAME = SERVICE_NAME + "DomainTransactionManager";
	public static final String PERSIST_UNIT = SERVICE_NAME + "Domain";

	@Bean(name = ENTITY_MANAGER_FACTORY_NAME)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			@Qualifier(INTEGRATION_DATA_SOURCE_NAME) DataSource integrationDataSource) {
		Map<String, String> jpaPropertyMap = jpaProperties().getProperties();
		Map<String, Object> hibernatePropertyMap =
				hibernateProperties().determineHibernateProperties(jpaPropertyMap, new HibernateSettings());

		return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), jpaPropertyMap, null)
				.dataSource(integrationDataSource)
				.properties(hibernatePropertyMap)
				.persistenceUnit(PERSIST_UNIT)
				.packages(BASE_PACKAGE)
				.build();
	}

	@Bean(name = TRANSACTION_MANAGER_NAME)
	public PlatformTransactionManager transactionManager(
			@Qualifier(ENTITY_MANAGER_FACTORY_NAME) EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	@ConfigurationProperties(prefix = SERVICE_NAME + ".datasource.write")
	public DataSource integrationWriteDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix = SERVICE_NAME + ".datasource.read")
	public DataSource integrationReadDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public DataSource integrationRoutingDataSource() {
		ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

		routingDataSource.setDefaultTargetDataSource(integrationWriteDataSource());
		routingDataSource.setTargetDataSources(this.getIntegrationTargetDataSources());

		return routingDataSource;
	}

	@Bean(name = INTEGRATION_DATA_SOURCE_NAME)
	public DataSource integrationDataSource() {
		return new LazyConnectionDataSourceProxy(integrationRoutingDataSource());
	}

	@Bean
	@ConfigurationProperties(prefix = SERVICE_NAME + ".jpa")
	public JpaProperties jpaProperties() {
		return new JpaProperties();
	}

	@Bean
	@ConfigurationProperties(prefix = SERVICE_NAME + ".jpa.hibernate")
	public HibernateProperties hibernateProperties() {
		return new HibernateProperties();
	}

	private Map<Object, Object> getIntegrationTargetDataSources() {
		Map<Object, Object> targetDataSourceMap = new HashMap<>();

		targetDataSourceMap.put(
				ReplicationRoutingDataSource.DatabaseType.READ, integrationReadDataSource());
		targetDataSourceMap.put(
				ReplicationRoutingDataSource.DatabaseType.WRITE, integrationWriteDataSource());

		return targetDataSourceMap;
	}
}
