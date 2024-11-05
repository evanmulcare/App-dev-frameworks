package ie.spring.lab1.services;

import ie.spring.lab1.repositories.weddings.Wedding;
import ie.spring.lab1.repositories.weddings.WeddingRepository;
import ie.spring.lab1.services.utilities.TidyMoney;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class CalculateCostImplementation implements CalculateCost {

    private WeddingRepository weddingRepository;

    // This would be better not hardcoded but suffices for now.
    // We can edit this later.
    @Value("${vat.rate}")
    private double tax_rate;

    @Autowired
    public void setWeddingRepository(WeddingRepository weddingRepository) {
        this.weddingRepository = weddingRepository;
    }
    @PostConstruct
    public void init() {
        log.info("CalculateCostImplementation created");
    }

    @PreDestroy
    public void cleanup() {
        log.info("CalculateCostImplementation destroyed.");
    }

    @Override
    public double calculateWeddingCostExVat(String weddingId) throws Exception {
        Optional<Wedding> weddingOptional = weddingRepository.findById(weddingId);
        if (weddingOptional.isPresent()) {
            Wedding wedding = weddingOptional.get();
            int numberOfGuests = weddingRepository.getNumberOfGuests(weddingId);
            return wedding.getCostPerGuest() * numberOfGuests;
        }
        throw new Exception("Wedding with id " + weddingId + " not found.");
    }



    @Override
    public double calculateWeddingCostIncVat(String weddingId) throws Exception {
        double cost = this.calculateWeddingCostExVat(weddingId) * (1+tax_rate);
        return TidyMoney.twoDigits(cost);
    }


}
