package com.example.project_frontend.frontend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.example.project_frontend.frontend.config.app.AppSecurityConfig;
import com.example.project_frontend.frontend.config.app.OAuth2ClientConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "com.example.project_frontend.frontend.security", "com.example.project_frontend.frontend.service",
		"com.example.project_frontend.frontend.repository", "com.example.project_frontend.frontend.aspect" })
@Import({ AppSecurityConfig.class/* , OAuth2ClientConfig.class */ })
public class AppConfig {
}