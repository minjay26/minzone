package cn.tendata.minzone.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import cn.tendata.minzone.manager.service.CustomUserService;

@Configuration
public class Security extends WebSecurityConfigurerAdapter {

	@Bean
	public CustomUserService customUserService(){
		return new CustomUserService();
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(customUserService());   	
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().ignoringAntMatchers("/login/**")
		        .and().authorizeRequests().anyRequest().authenticated()
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/home").permitAll()
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
		
//		http.csrf().ignoringAntMatchers("/login/**")
//        .and().authorizeRequests().anyRequest().permitAll()
//		.and().formLogin().loginPage("/login").defaultSuccessUrl("/home")
//		.and().logout().permitAll();
	}
}
