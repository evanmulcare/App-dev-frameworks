package ie.spring.lab1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(LanguageConfig.class)
public class LanguageConfigTests {

    @Autowired
    private MessageSource messageSource;

    @Test
    void messageSourceBeanIsNotNull() {
        assertNotNull(messageSource, "MessageSource bean should not be null");
    }

    @Test
    void testDefaultLanguage() {
        String greeting = messageSource.getMessage("greeting", null, "Default Greeting", null);
        assertEquals("Hi", greeting);
    }

    @Test
    void testSpanishLanguage() {
        String greeting = messageSource.getMessage("greeting", null, "Default Greeting", Locale.forLanguageTag("fr"));
        assertEquals("bonjour", greeting);
    }
}
