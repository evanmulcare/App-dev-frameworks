package ie.spring.jdbc; // Ensure this is the correct package

import ie.spring.jdbc.repo.CartoonRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class CartoonRepoTests {
    @Autowired
    private CartoonRepo cartoonRepo;
}
