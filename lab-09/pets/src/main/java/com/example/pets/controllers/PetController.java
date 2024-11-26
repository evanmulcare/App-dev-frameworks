package com.example.pets.controllers;

import com.example.pets.dtos.PetDTO;
import com.example.pets.entities.Household;
import com.example.pets.entities.Pet;
import com.example.pets.services.HouseholdService;
import com.example.pets.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final HouseholdService householdService;


    @Autowired
    public PetController(PetService petService, HouseholdService householdService) {
        this.petService = petService;
        this.householdService = householdService;
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.readAllPets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id) {
        Pet pet = petService.readPetByID(id);
        return pet != null ? ResponseEntity.ok(pet) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePetByID(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody @Valid PetDTO petDTO) {
        Household household = householdService.findHouseholdByEircodeWithPets(petDTO.getHouseholdEircode())
                .orElseThrow(() -> new IllegalArgumentException("Invalid household Eircode"));

        Pet pet = new Pet();
        pet.setName(petDTO.getName());
        pet.setAnimalType(petDTO.getAnimalType());
        pet.setBreed(petDTO.getBreed());
        pet.setAge(petDTO.getAge());
        pet.setHousehold(household);

        Pet createdPet = petService.createPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pet> changePetName(@PathVariable Long id, @RequestBody String newName) {
        Pet pet = petService.readPetByID(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }

        pet.setName(newName);
        Pet updatedPet = petService.createPet(pet);
        return ResponseEntity.ok(updatedPet);
    }
}
