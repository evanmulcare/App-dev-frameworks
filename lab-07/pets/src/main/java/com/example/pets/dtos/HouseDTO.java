package com.example.pets.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HouseDTO {
    @NotNull(message = "Eircode cannot be null")
    @Size(min = 8, max = 8, message = "Eircode must be exactly 8 characters")
    private String eircode;

    @Positive(message = "Number of occupants must be greater than 0")
    private int numberOfOccupants;

    @Positive(message = "Maximum number of occupants must be greater than 0")
    private int maxNumberOfOccupants;

    @NotNull(message = "Owner occupied status cannot be null")
    private boolean ownerOccupied;
}
