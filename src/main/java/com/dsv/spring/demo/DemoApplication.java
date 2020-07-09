package com.dsv.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SpringBootApplication
public class DemoApplication {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/jsp/");
		bean.setSuffix(".jsp");
		return bean;
	}
	
	 @EnableWebSecurity
	public class MemorySecurityConfiguration extends WebSecurityConfigurerAdapter {

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	            .inMemoryAuthentication()
	            .withUser("claude")
	                .password("{noop}claude")
	                .roles("admin", "dev")
	            .and().withUser("ced")
	                .password("{noop}ced")
	                .roles("admin");
	    }
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
