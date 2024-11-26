package com.example.pets.services;

import com.example.pets.dtos.PetNameAndBreedDto;
import com.example.pets.entities.Pet;
import com.example.pets.exceptions.InvalidPetException;
import com.example.pets.repo.PetRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService  {
    private final PetRepo petRepo;

    @Autowired
    public PetServiceImpl(PetRepo petRepo) {
        this.petRepo = petRepo;
    }

    @Override
    public Pet createPet(Pet pet) {
        if (pet.getName() == null || pet.getAnimalType() == null || pet.getBreed() == null) {
            throw new InvalidPetException("Pet data is incomplete.");
        }
        return petRepo.save(pet);
    }

    @Override
    public Pet updatePetDetails(Long id, Pet updatedPet) {
        Pet existingPet = petRepo.readPetByID(id);
        if (existingPet == null) {
            throw new EntityNotFoundException("Pet with ID " + id + " not found.");
        }

        existingPet.setName(updatedPet.getName());
        existingPet.setAge(updatedPet.getAge());
        existingPet.setAnimalType(updatedPet.getAnimalType());
        existingPet.setBreed(updatedPet.getBreed());

        return petRepo.save(existingPet);
    }

    @Override
    public List<Pet> readAllPets() {
        return petRepo.readAllPets();
    }

    @Override
    public Pet readPetByID(Long id) {
        return petRepo.readPetByID(id);
    }

    @Override
    public void deletePetByID(Long id) {
        petRepo.deletePetByID(id);
    }

    @Override
    public void deletePetsByName(String name) {
        petRepo.deletePetsByName(name);
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepo.findPetsByAnimalType(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepo.findPetsByBreedOrderByAge(breed);
    }

    @Override
    public List<PetNameAndBreedDto> getNameAndBreedOnly() {
        return petRepo.getNameAndBreedOnly();
    }

    @Override
    public Double getAverageAge() {
        return petRepo.getAverageAge();
    }

    @Override
    public Integer getOldestAge() {
        return petRepo.getOldestAge();
    }

}
