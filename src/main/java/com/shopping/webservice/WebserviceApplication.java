package com.shopping.webservice;

import com.shopping.webservice.entity.audit.AuditorAwareImpl;
import com.shopping.webservice.entity.audit.LocalDateTimeProvider;
import com.shopping.webservice.properties.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value={SecurityProperties.class})
@EnableJpaAuditing(auditorAwareRef = "auditorAware",dateTimeProviderRef = "dateTimeProvider",modifyOnCreate = false)
public class WebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebserviceApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

	@Bean
	public DateTimeProvider dateTimeProvider(){return new LocalDateTimeProvider();
	}

}
