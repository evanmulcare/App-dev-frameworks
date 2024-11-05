package ie.spring.lab1.repositories.weddings;

import java.util.Optional;

public interface WeddingRepository {
    int getNumberOfGuests(String id);
    Optional<Wedding> findById(String id);
}
