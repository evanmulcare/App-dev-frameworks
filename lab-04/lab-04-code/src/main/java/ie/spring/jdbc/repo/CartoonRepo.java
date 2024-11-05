package ie.spring.jdbc.repo;

import ie.spring.jdbc.entites.Cartoon;

import java.util.List;

public interface CartoonRepo {
    int count();
    List<Cartoon> findAll();
    Cartoon findByCartoonId(int id);
    void delete(int id);
}
