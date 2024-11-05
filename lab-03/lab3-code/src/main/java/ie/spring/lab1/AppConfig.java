package ie.spring.lab1;

import ie.spring.lab1.repositories.weddings.MockWeddingRepositoryImpl;
import ie.spring.lab1.repositories.weddings.WeddingRepository;
import ie.spring.lab1.services.CalculateCost;
import ie.spring.lab1.services.CalculateCostImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AppConfig {
    @Bean
    public WeddingRepository weddingRepository() {
        return new MockWeddingRepositoryImpl();
    }

    @Bean
    public CalculateCost calculateCost(WeddingRepository weddingRepository) {
        return new CalculateCostImplementation(weddingRepository);
    }
}
