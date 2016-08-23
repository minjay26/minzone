package cn.tendata.minzone.webapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
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
     @EntityScan(basePackages = "cn.tendata.minzone.manager.data.domain")
     static class JpaConfig {
//       @Bean
//   	  public AuditorAware<User> auditorProvider() {
//   	    return new UserAuditorAware();
//   	  }
    	 
     }
     
     @Configuration
     @ComponentScan(basePackages="cn.tendata.minzone.manager.service")
     static class ServiceConfig {}
     
//     @EnableCaching
//     static class CacheConfig{  	 
//    	 
//    	    @Bean
//    	    public CacheManager cacheManager() {
//    	        return new EhCacheCacheManager(ehCache().getObject());
//    	    }
//    	    
//    	 
//    	    @Bean
//    	    public EhCacheManagerFactoryBean ehCache() {
//    	        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
//    	        cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
//    	        return cmfb;
//    	    }
//     }
}
