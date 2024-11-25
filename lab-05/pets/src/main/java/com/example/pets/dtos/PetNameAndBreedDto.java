package com.example.pets.dtos;

public class PetNameAndBreedDto {

    private String name;
    private String animalType;
    private String breed;

    public PetNameAndBreedDto(String name, String animalType, String breed) {
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}