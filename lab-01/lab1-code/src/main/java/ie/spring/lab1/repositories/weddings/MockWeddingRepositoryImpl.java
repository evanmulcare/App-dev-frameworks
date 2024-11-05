package ie.spring.lab1.repositories.weddings;

// This simulates a data source

import ie.spring.lab1.repositories.basic.Person;
import lombok.ToString;

import java.util.*;

public class MockWeddingRepositoryImpl implements WeddingRepository {
    Map<String, Wedding> weddings = new HashMap<>();
    List<Guest> guests = new ArrayList<>();

    public MockWeddingRepositoryImpl() {

        Guest g1 = new Guest(1L,
                new Person(20,"Fred", "O'Brien" ),
                null,
                "089-6758475", "fobrien@gmail.com",
                "21 Argyle Road, Blackrock, Cork",
                "RS342");
        Guest g2 = new Guest(2L,
                new Person(20,"Laura", "Bellingham" ),
                new Person(21,"Maura", "Murphy" ),
                "087-7683451",
                "lorbell@gmail.com",
                "1 Green Park, Bishopstown, Cork",
                "RS342");
        guests.add(g1);
        guests.add(g2);

        Wedding wedding = new Wedding("RS342",
                new Person(55, "Sharon", "Willis"),
                new Person(56, "Mark", "Wilson"),
                120.00, guests);
        weddings.put(wedding.getWeddingId(), wedding);
    }

    @Override
    public int getNumberOfGuests(String id) {
        return (int) (guests.size() * 2 - guests.stream().filter(g -> g.getPlusOne()==null).count());
    }

    @Override
    public Optional<Wedding> findById(String id) {
        return Optional.ofNullable(weddings.get(id));
    }
}
