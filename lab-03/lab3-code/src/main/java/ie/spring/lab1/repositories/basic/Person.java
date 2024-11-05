package ie.spring.lab1.repositories.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// This is a DTO (data transfer object) because it is only used to
// transfer data between layers in the application. It really doesn't
// do anything else.
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Person {
    private int personId;
    private String firstName;
    private String lastName;
}
