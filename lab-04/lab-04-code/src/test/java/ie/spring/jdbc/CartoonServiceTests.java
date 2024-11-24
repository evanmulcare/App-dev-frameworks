package ie.spring.jdbc;

import ie.spring.jdbc.configurations.Config;
import ie.spring.jdbc.entites.Cartoon;
import ie.spring.jdbc.exceptions.DataConflictException;
import ie.spring.jdbc.exceptions.MalformedDataException;
import ie.spring.jdbc.exceptions.NotFoundException;
import ie.spring.jdbc.service.CartoonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(Config.class)
public class CartoonServiceTests {
    @Autowired
    private CartoonService cartoonService;

    @Test
    public void findById_shouldReturnCartoon() throws NotFoundException {
        Cartoon cartoon = cartoonService.findById(2);
        Assertions.assertEquals("Pikachu", cartoon.getName());
    }

    @Test
    public void findById_notFound_shouldThrowNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            cartoonService.findById(111);
        });
    }

    @Test
    public void findAll_shouldReturnAllCartoons(){
        List<Cartoon> cartoons = cartoonService.findAll();
        Assertions.assertEquals(10, cartoons.size());
    }

    @Test
    public void count_shouldReturnCountOfCartoons(){
        int count = cartoonService.count();
        Assertions.assertEquals(10, count);
    }
    @Test
    public void save_idAlreadyExists_shouldThrowDataConflictException() throws DataConflictException, MalformedDataException {
        Cartoon cartoon = new Cartoon(1, "Muggles Unite", 2024);
        Assertions.assertThrows(DataConflictException.class, () -> cartoonService.save(cartoon));
    }

    @Test
    public void save_noName_shouldThrowMalformedDataException() throws DataConflictException, MalformedDataException {
        Cartoon cartoon = new Cartoon(11, "", 2024);
        Assertions.assertThrows(MalformedDataException.class, () -> cartoonService.save(cartoon));
    }

    @Test
    public void save_nullName_shouldThrowMalformedDataException() throws DataConflictException, MalformedDataException {
        Cartoon cartoon = new Cartoon(11, null, 2024);
        Assertions.assertThrows(MalformedDataException.class, () -> cartoonService.save(cartoon));
    }

    @Test
    public void delete_shouldDeleteCartoon() throws NotFoundException {
        cartoonService.delete(2);
        Assertions.assertThrows(NotFoundException.class, () -> cartoonService.findById(2));
    }

    @Test
    public void save_badYear_shouldThrowMalformedDataException() throws DataConflictException, MalformedDataException {
        Cartoon cartoon = new Cartoon(11, "Muggles Unite", 1975);
        Assertions.assertThrows(MalformedDataException.class, () -> cartoonService.save(cartoon));
    }
}
