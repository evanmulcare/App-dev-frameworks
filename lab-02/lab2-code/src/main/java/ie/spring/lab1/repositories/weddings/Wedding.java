package ie.spring.lab1.repositories.weddings;

import ie.spring.lab1.repositories.basic.Person;
import jakarta.annotation.Nullable;
import lombok.*;

import java.util.List;

// This is a DTO (data transfer object) because it is only used to
// transfer data between layers in the application. It really doesn't
// do anything else.
// A wedding has a list of guests. Each guest has a potential plus one.

@Getter
@Setter
@AllArgsConstructor
public class Wedding {
    private String weddingId;
    private Person person1, person2;
    private double costPerGuest;
    @Nullable
    private List<Guest> guests;
}
