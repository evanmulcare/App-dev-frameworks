package ie.spring.jdbc;

import ie.spring.jdbc.configurations.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(Config.class)
class DemoApplicationTests {
    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(context);
    }

}
