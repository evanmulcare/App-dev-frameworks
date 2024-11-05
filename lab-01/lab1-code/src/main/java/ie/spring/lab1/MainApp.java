package ie.spring.lab1;

import ie.spring.lab1.repositories.weddings.MockWeddingRepositoryImpl;
import ie.spring.lab1.repositories.weddings.WeddingRepository;
import ie.spring.lab1.services.CalculateCost;
import ie.spring.lab1.services.CalculateCostImplementation;

public class MainApp {
    public static void main(String[] args)  {
        WeddingRepository weddingRepository = new MockWeddingRepositoryImpl();
        CalculateCost calculateCost = new CalculateCostImplementation(weddingRepository);

        String weddingId = "RS342";
        weddingRepository.findById(weddingId).ifPresentOrElse(
            wedding -> System.out.println("Wedding Details: " + wedding),
            () -> System.out.println("Wedding with ID " + weddingId + " not found.")
        );

        try {
            double costExVat = calculateCost.calculateWeddingCostExVat(weddingId);
            System.out.println("Wedding Cost Excluding VAT: " + costExVat);
        
            double costIncVat = calculateCost.calculateWeddingCostIncVat(weddingId);
            System.out.println("Wedding Cost Including VAT: " + costIncVat);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
