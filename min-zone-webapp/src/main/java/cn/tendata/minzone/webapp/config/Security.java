package cn.tendata.minzone.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import cn.tendata.minzone.manager.service.CustomUserService;

@Configuration
public class Security extends WebSecurityConfigurerAdapter {

	@Bean
	public CustomUserService customUserService(){
		return new CustomUserService();
	}

	@Override
	protected UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		return customUserService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll()// 所有需要经过认证登录后才能访问
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/home/1")
				.and().csrf().ignoringAntMatchers("/login/**");
	}
}
