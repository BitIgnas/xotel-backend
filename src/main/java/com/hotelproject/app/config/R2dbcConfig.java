package com.hotelproject.app.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class R2dbcConfig {

    @Value("${r2dbc.postgres.user}")
    private String user;

    @Value("${r2dbc.postgres.password}")
    private String password;

    @Value("${r2dbc.postgres.database}")
    private String database;

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "postgresql")
                        .option(HOST, "localhost")
                        .option(PORT, 5432)
                        .option(USER, user)
                        .option(PASSWORD, password)
                        .option(DATABASE, database)
                        .option(MAX_SIZE, 40)
                        .build());
    }
}
