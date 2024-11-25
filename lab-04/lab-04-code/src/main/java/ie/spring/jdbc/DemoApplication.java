package ie.spring.jdbc;

import ie.spring.jdbc.entites.Cartoon;
import ie.spring.jdbc.repo.CartoonRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class DemoApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

        CartoonRepo cartoonRepo = applicationContext.getBean(CartoonRepo.class);
        List<Cartoon> cartoons = cartoonRepo.findAll();
        cartoons.forEach(cartoon -> System.out.println(cartoon.getName()));
    }
}
