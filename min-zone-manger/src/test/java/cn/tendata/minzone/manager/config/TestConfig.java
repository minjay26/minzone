package cn.tendata.minzone.manager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import cn.tendata.minzone.manager.data.jpa.config.DataConfig;

@Profile("test")
@Configuration
@ComponentScan
@Import({MvcConfig.class,DataConfig.class})
public class TestConfig {

}
