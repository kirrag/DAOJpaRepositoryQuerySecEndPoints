package ru.netology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password(encoder().encode("Welcome@1"))
			.authorities("read", "write");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.and()
			.authorizeHttpRequests().antMatchers(HttpMethod.GET, "/persons").hasAuthority("read")
			.and()
			.authorizeHttpRequests().antMatchers(HttpMethod.GET).permitAll()
			.and()
			.authorizeHttpRequests().antMatchers(HttpMethod.POST).denyAll()
			.and()
			.authorizeHttpRequests().antMatchers(HttpMethod.DELETE).denyAll()
			.and()
			.authorizeHttpRequests().antMatchers(HttpMethod.PATCH).denyAll()
			.and()
			.authorizeHttpRequests().antMatchers(HttpMethod.PUT).denyAll()
			.and()
			.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/persons").hasAuthority("write")
			.and()
			.authorizeHttpRequests().antMatchers(HttpMethod.PUT, "/persons").hasAuthority("write")
			.and()
			.authorizeHttpRequests().antMatchers(HttpMethod.DELETE, "/persons").hasAuthority("write")
			.and()
			.authorizeHttpRequests().antMatchers(HttpMethod.DELETE, "/persons/by-personid").hasAuthority("write")
			.and()
			.authorizeHttpRequests().anyRequest().authenticated();
	}
}


