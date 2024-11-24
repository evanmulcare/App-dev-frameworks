package ie.spring.jdbc.service;

import ie.spring.jdbc.entites.Cartoon;
import ie.spring.jdbc.exceptions.DataConflictException;
import ie.spring.jdbc.exceptions.MalformedDataException;

import java.util.List;

public interface CartoonService {
    int count();
    Cartoon findById(int id);
    List<Cartoon> findAll();
    void delete(int id);
    void save(Cartoon cartoon) throws DataConflictException, MalformedDataException;

}
