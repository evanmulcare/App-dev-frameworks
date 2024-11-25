package com.example.pets.repo;

import com.example.pets.dtos.PetNameAndBreedDto;
import com.example.pets.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PetRepo  extends JpaRepository<Pet, Long> {

    List<Pet> readAllPets();

    Pet readPetByID(@Param("id") Long id);

    @Transactional
    void deletePetByID(@Param("id") Long id);

    @Transactional
    void deletePetsByName(@Param("name") String name);

    List<Pet> findPetsByAnimalType(@Param("animalType") String animalType);

    List<Pet> findPetsByBreedOrderByAge(@Param("breed") String breed);

    @Query("SELECT new com.example.pets.dtos.PetNameAndBreedDto(p.name, p.animalType, p.breed) FROM Pet p")
    List<PetNameAndBreedDto> getNameAndBreedOnly();

    @Query("SELECT AVG(p.age) FROM Pet p")
    Double getAverageAge();  // Custom query for average age.

    @Query("SELECT MAX(p.age) FROM Pet p")
    Integer getOldestAge();
}
