package com.example.pets.dtos;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class PetDTO {
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "Animal type cannot be null")
    @Size(min = 1, max = 50, message = "Animal type must be between 1 and 50 characters")
    private String animalType;

    @NotNull(message = "Breed cannot be null")
    @Size(min = 1, max = 50, message = "Breed must be between 1 and 50 characters")
    private String breed;

    @Positive(message = "Age must be greater than 0")
    private int age;

    @NotNull(message = "Household eircode cannot be null")
    private String householdEircode;
}
