package ie.spring.lab1;

import ie.spring.lab1.repositories.weddings.MockWeddingRepositoryImpl;
import ie.spring.lab1.repositories.weddings.WeddingRepository;
import ie.spring.lab1.services.CalculateCost;
import ie.spring.lab1.services.CalculateCostImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class MainApp {
    public static void main(String[] args)  {
      //  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicationContext context = SpringApplication.run(Config.class);

        WeddingRepository weddingRepository = context.getBean(WeddingRepository.class);
        weddingRepository.findById("RS342");

        CalculateCost calculateCost = context.getBean(CalculateCost.class);
        try {
            double costExVat = calculateCost.calculateWeddingCostExVat("RS342");
            System.out.println("Wedding Cost Excluding VAT: " + costExVat);

            double costIncVat = calculateCost.calculateWeddingCostIncVat("RS342");
            System.out.println("Wedding Cost Including VAT: " + costIncVat);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        MessageSource messageSource = context.getBean(MessageSource.class);

        System.out.println("Greeting (Default Locale): " + messageSource.getMessage("greeting", null, Locale.getDefault()));

        Locale frenchLocale = new Locale("fr");
        System.out.println("Greeting (French): " + messageSource.getMessage("greeting", null, frenchLocale));
    }
}
