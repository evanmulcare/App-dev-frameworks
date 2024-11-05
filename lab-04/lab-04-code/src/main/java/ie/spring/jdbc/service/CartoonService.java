package ie.spring.jdbc.service;

import ie.spring.jdbc.entites.Cartoon;

import java.util.List;

public interface CartoonService {
    int count();
    Cartoon findByCartoonId(int id);
    List<Cartoon> findAll();
    void delete(int id);
}
