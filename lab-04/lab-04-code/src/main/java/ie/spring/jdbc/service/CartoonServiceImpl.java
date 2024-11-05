package ie.spring.jdbc.service;

import ie.spring.jdbc.entites.Cartoon;
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
    public Cartoon findByCartoonId(int id) {
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
}
