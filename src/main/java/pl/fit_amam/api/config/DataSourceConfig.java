package pl.fit_amam.api.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.fit_amam.api.aws.SSMClient;

import javax.sql.DataSource;

@Configuration
@Profile("default")
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(SSMClient.getParameter("amam.spring.datasource.url", false));
        dataSourceBuilder.username(SSMClient.getParameter("amam.spring.datasource.username", false));
        dataSourceBuilder.password(SSMClient.getParameter("amam.spring.datasource.password", false));
        return dataSourceBuilder.build();
    }
}