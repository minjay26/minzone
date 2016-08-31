package cn.tendata.minzone.manager.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.tendata.minzone.manager.bind.support.CurrentUserHandlerMethodArgumentResolver;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages="cn.tendata.minzone.manager")
public class MvcConfig extends WebMvcConfigurerAdapter{
   
//	
//	@Bean
//	public TemplateResolver templateResolver() {
//		TemplateResolver templateResolver=new ServletContextTemplateResolver();
//		templateResolver.setPrefix("/templates");
//		templateResolver.setSuffix(".html");
//		templateResolver.setTemplateMode("HTML5");
//		return templateResolver;
//	}
//	
//	@Bean
//	public SpringTemplateEngine templateEngine(){
//		SpringTemplateEngine templateEngine=new SpringTemplateEngine();
//		templateEngine.setTemplateResolver(templateResolver());
//		return templateEngine;
//	}
//	
//	@Bean
//	public ThymeleafViewResolver thymeleafViewResolver(){
//		ThymeleafViewResolver thymeleafViewResolver=new ThymeleafViewResolver();
//		thymeleafViewResolver.setTemplateEngine(templateEngine());
//		return thymeleafViewResolver;
//	}
	
	      @Bean
	    public CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver() {
	        return new CurrentUserHandlerMethodArgumentResolver();
	    }
	      
	      @Override
	      public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	          argumentResolvers.add(currentUserHandlerMethodArgumentResolver());
	     
	      }

}
