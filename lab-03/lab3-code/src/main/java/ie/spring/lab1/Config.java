package ie.spring.lab1;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppConfig.class, LanguageConfig.class})
public class Config {
}
