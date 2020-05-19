package pl.fit_amam.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.time.ZonedDateTime;
import java.util.UUID;

@Configuration
@Profile("test")
public class H2JpaConfig {

	@Bean
	public DataSource dataSourceH2() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.setName("DataBaseTestConfig" + ZonedDateTime.now() + UUID.randomUUID()).build();
	}
}