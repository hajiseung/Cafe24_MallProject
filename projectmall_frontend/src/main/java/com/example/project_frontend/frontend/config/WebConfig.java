package com.example.project_frontend.frontend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.example.project_frontend.frontend.config.web.FileuploadConfig;
import com.example.project_frontend.frontend.config.web.MVCConfig;
import com.example.project_frontend.frontend.config.web.MessageConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "com.example.project_frontend.frontend.controller",
		"com.example.project_frontend.frontend.exception" })
@Import({ MVCConfig.class, FileuploadConfig.class, MessageConfig.class })
public class WebConfig {
}
