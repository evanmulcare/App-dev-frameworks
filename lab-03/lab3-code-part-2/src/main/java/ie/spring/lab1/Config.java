package ie.spring.lab1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@ComponentScan({"ie.spring.lab1"})
@Configuration
@PropertySource("classpath:application.properties")
@Import({LanguageConfig.class})
public class Config {
}
