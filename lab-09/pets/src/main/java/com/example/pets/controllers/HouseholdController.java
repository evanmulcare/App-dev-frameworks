package com.example.pets.controllers;

import com.example.pets.dtos.HouseDTO;
import com.example.pets.entities.Household;
import com.example.pets.services.HouseholdService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/households")
public class HouseholdController {
    private final HouseholdService householdService;

    @Autowired
    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping
    public List<Household> getAllHousehold() {
        return householdService.findAllHouseholds();
    }

    @GetMapping("/no-pets")
    public List<Household> getHouseholdsWithNoPets() {
        return householdService.findHouseholdsWithNoPets();
    }

    @GetMapping("/{eircode}")
    public ResponseEntity<Household> getHousehold(@PathVariable String eircode) {
        Optional<Household> household = householdService.findHouseholdByEircodeWithPets(eircode);
        return household.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{eircode}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHousehold(eircode);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Household> createHousehold(@RequestBody @Valid HouseDTO houseDTO) {
        Household household = new Household(
                houseDTO.getEircode(),
                houseDTO.getNumberOfOccupants(),
                houseDTO.getMaxNumberOfOccupants(),
                houseDTO.isOwnerOccupied(),
                null
        );
        Household createdHousehold = householdService.createHousehold(household);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHousehold);
    }
}
