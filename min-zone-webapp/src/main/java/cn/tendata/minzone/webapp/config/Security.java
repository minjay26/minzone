package cn.tendata.minzone.webapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import cn.tendata.minzone.manager.service.CustomUserService;


@Configuration
public class Security extends WebSecurityConfigurerAdapter{
   
	
	@Bean
    UserDetailsService customUserService(){
    	return new CustomUserService();
    }
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService());
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}
	
	 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.csrf().disable().authorizeRequests()//.antMatchers("/user/**").permitAll()
    	 .anyRequest().permitAll()//.authenticated()//所有需要经过认证登录后才能访问
    	 .and()
    	 .formLogin()
    	 .loginPage("/login")//定制登录行为
    	 .defaultSuccessUrl("/home")
    	 .permitAll()
    	 .and()
    	 .logout().deleteCookies().invalidateHttpSession(true).permitAll();
    	 http.sessionManagement()/*.sessionAuthenticationErrorUrl("/invalidSession")*/.maximumSessions(1);
    	  http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
    }
}
