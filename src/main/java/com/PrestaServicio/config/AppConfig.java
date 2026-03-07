package com.PrestaServicio.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.PrestaServicio.model.BaseData;
import com.PrestaServicio.util.consts.EnvironmentConst;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class AppConfig implements WebMvcConfigurer{

    @Autowired
    Environment env;

    @Bean
    @Primary
    DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(env.getProperty(EnvironmentConst.DB_URL));
        config.setUsername(env.getProperty(EnvironmentConst.dbUsername));
        config.setPassword(env.getProperty(EnvironmentConst.dbPassword));
        config.setDriverClassName(env.getProperty(EnvironmentConst.dbClassName));

        return new HikariDataSource(config);
    }

    @Bean
    BaseData baseData() {
        BaseData data = new BaseData();
        // puedes leerlo del properties también
        data.setSchema(env.getRequiredProperty(EnvironmentConst.SPRING_DATASOURCE_HIKARI_SCHEMA));
        return data;
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
	ObjectMapper objectMapper() {
		JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);

        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);

		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            
		// Ignora los campos nulos
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		return objectMapper;
    }

}
