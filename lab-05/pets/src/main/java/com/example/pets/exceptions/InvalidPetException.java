package com.example.pets.exceptions;

public class InvalidPetException extends RuntimeException {
    public InvalidPetException(String message) {
        super(message);
    }
}