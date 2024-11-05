package ie.spring.lab1.services;

public interface CalculateCost {
    double calculateWeddingCostExVat(String weddingId) throws Exception;
    double calculateWeddingCostIncVat(String weddingId) throws Exception;
}
