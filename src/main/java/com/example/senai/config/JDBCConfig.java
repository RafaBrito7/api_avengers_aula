package com.example.senai.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class JDBCConfig {

	DataSource dataSource;
	String url = "jdbc:postgresql://localhost:5432/shield";
	String user = "gustavo";
	String pass = "gustavo";
	
	public static void main(String[] args) throws SQLException {
		JDBCConfig jdbcConfig = new JDBCConfig();
		for (int i = 0; i < 30; i++) {
			jdbcConfig.getConnection();
			System.out.println(i);
		}
	}

	public JDBCConfig() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl(url);
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(pass);
		
		comboPooledDataSource.setMaxPoolSize(15);
		this.dataSource = comboPooledDataSource;
	}

	@Bean
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
