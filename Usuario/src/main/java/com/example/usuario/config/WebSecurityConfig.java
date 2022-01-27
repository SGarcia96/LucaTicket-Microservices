package com.example.usuario.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.usuario.security.JWTAuthorizationFilter;

import org.springframework.http.HttpMethod;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().antMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
				.antMatchers(HttpMethod.GET, "/usuarios").permitAll().anyRequest().authenticated();

		// Voy a dejar esto hasta que tengamos todos los métodos luego lo borramos
		/*
		 * .antMatchers(HttpMethod.GET, "/user/**").hasRole("USER")
		 * .antMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
		 * .antMatchers(HttpMethod.PUT, "/user/**").hasRole("ADMIN")
		 * .antMatchers(HttpMethod.PATCH, "/user/**").hasRole("ADMIN")
		 * .antMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
		 * 
		 */
	}
}
