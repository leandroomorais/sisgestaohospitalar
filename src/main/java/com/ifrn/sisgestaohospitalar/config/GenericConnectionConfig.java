package com.ifrn.sisgestaohospitalar.config;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericConnectionConfig {

	@Bean
	public Connection connection(DataSource dataSource) throws SQLException {
		return dataSource.getConnection();
	}

}
