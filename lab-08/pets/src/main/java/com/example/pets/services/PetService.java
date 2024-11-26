package com.example.pets.services;

import com.example.pets.dtos.PetNameAndBreedDto;
import com.example.pets.entities.Pet;

import java.util.List;

public interface PetService {
    Pet createPet(Pet pet);

    List<Pet> readAllPets();

    Pet readPetByID(Long id);

    Pet updatePetDetails(Long id, Pet updatedPet);

    void deletePetByID(Long id);

    void deletePetsByName(String name);

    List<Pet> findPetsByAnimalType(String animalType);

    List<Pet> findPetsByBreed(String breed);

    List<PetNameAndBreedDto> getNameAndBreedOnly();

    Double getAverageAge();

    Integer getOldestAge();
}
