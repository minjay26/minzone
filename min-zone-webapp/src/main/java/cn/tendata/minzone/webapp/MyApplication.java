package cn.tendata.minzone.webapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MyApplication extends SpringBootServletInitializer{
	
 
     public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	
	}
     
     @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	return application.sources(MyApplication.class) ;
    }
     
     @Configuration
     @EnableTransactionManagement
     @EnableJpaRepositories(basePackages = "cn.tendata.minzone.manager.repository")
     @EnableJpaAuditing
     @EntityScan(basePackages = "cn.tendata.minzone.manager.model.entity")
     static class JpaConfig {}
     
     @Configuration
     @ComponentScan(basePackages="cn.tendata.minzone.manager.service")
     static class ServiceConfig {}
}
