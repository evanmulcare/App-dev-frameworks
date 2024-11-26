package com.example.pets.controllers;

import com.example.pets.dtos.HouseholdInput;
import com.example.pets.entities.Household;
import com.example.pets.entities.Pet;
import com.example.pets.services.HouseholdService;
import com.example.pets.services.PetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class GraphQLAPI {

    private PetService petService;
    private HouseholdService householdService;

    @QueryMapping
    public List<Household> getAllHouseholds() {
        return householdService.findAllHouseholds();
    }

    @QueryMapping
    public List<Pet> getAllPetsByAnimalType(@Argument String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    @QueryMapping
    public Household getHousehold(@Argument String eircode) {
        Optional<Household> household = householdService.findHouseholdByEircodeWithPets(eircode);
        return household.orElse(null);
    }

    @QueryMapping
    public Pet getPet(@Argument Long id) {
        return petService.readPetByID(id);
    }
    @MutationMapping
    public Household createNewHousehold(@Valid @Argument("household") HouseholdInput input) {
        Household household = new Household(
                input.eircode(),
                input.numberOfOccupants(),
                input.maxNumberOfOccupants(),
                input.ownerOccupied(),
                null
        );
        return householdService.createHousehold(household);
    }

    @MutationMapping
    public Boolean deleteHouseholdById(@Argument String eircode) {
        try {
            householdService.deleteHousehold(eircode);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @MutationMapping
    public Boolean deletePetById(@Argument Long id) {
        try {
            petService.deletePetByID(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
