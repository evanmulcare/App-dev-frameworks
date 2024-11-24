package ie.spring.jdbc.service;

import ie.spring.jdbc.entites.Cartoon;
import ie.spring.jdbc.exceptions.DataConflictException;
import ie.spring.jdbc.exceptions.MalformedDataException;
import ie.spring.jdbc.repo.CartoonRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartoonServiceImpl implements CartoonService {
    private final CartoonRepo cartoonRepo;

    public CartoonServiceImpl(CartoonRepo cartoonRepo) {
        this.cartoonRepo = cartoonRepo;
    }

    @Override
    public int count() {
        return cartoonRepo.count();
    }

    @Override
    public Cartoon findById(int id) {
        return cartoonRepo.findByCartoonId(id);
    }

    @Override
    public List<Cartoon> findAll() {
        return cartoonRepo.findAll();
    }

    @Override
    public void delete(int id) {
        cartoonRepo.delete(id);
    }

    @Override
    public void save(Cartoon cartoon) throws DataConflictException, MalformedDataException {
        if (cartoon.getName() == null) {
            throw new MalformedDataException("Cartoon name cannot be null");
        }

        if (cartoon.getFirstAppearanceYear() < 1980) {
            throw new MalformedDataException("Cartoon must be released before 1989");
        }

        if (cartoonRepo.findByCartoonId(cartoon.getCartoonId()) != null) {
            throw new DataConflictException("Cartoon already exists. with ID : " + cartoon.getCartoonId());
        }
    }
}
