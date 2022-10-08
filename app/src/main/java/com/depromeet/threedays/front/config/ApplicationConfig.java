package com.depromeet.threedays.front.config;

import com.depromeet.threedays.data.DataConfig;
import com.depromeet.threedays.data.JpaDataSourceConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration(
		exclude = {
			DataSourceAutoConfiguration.class,
			DataSourceTransactionManagerAutoConfiguration.class,
			HibernateJpaAutoConfiguration.class
		})
@ComponentScan({
	"com.depromeet.threedays",
})
@Import({JpaDataSourceConfig.class, DataConfig.class})
public class ApplicationConfig {}
