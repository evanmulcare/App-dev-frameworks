package com.example.pets.services;

import com.example.pets.entities.Household;
import com.example.pets.repo.HouseholdRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepo householdRepo;

    @Autowired
    public HouseholdServiceImpl(HouseholdRepo householdRepo) {
        this.householdRepo = householdRepo;
    }

    @Override
    public Household createHousehold(Household household) {
        return householdRepo.save(household);
    }

    @Override
    public void deleteHousehold(String eircode) {
        householdRepo.deleteById(eircode);
    }

    @Override
    public List<Household> findAllHouseholds() {
        return householdRepo.findAll();
    }

    @Override
    public Optional<Household> findHouseholdByEircodeWithPets(String eircode) {
        return householdRepo.findByEircodeWithPetsEagerlyLoaded(eircode);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepo.findHouseholdsWithNoPets();
    }


}
