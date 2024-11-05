package ie.spring.lab1;

import ie.spring.lab1.repositories.weddings.MockWeddingRepositoryImpl;
import ie.spring.lab1.repositories.weddings.WeddingRepository;
import ie.spring.lab1.services.CalculateCost;
import ie.spring.lab1.services.CalculateCostImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class Lab1ApplicationTests {
    private final CalculateCost calculateCost;

    // A constructor sets up the wedding repository (mock data source in this case) which is injected
    // into the calculateCostImplementation object to create the calculateCost object.
    // Note 1: this could be a @BeforeEach-annotated method instead of a constructor so that each test
    // starts with a clean data source but here it doesn't matter because we are not editing
    // the data source.
    // Note 2: we are testing the interfaces, so the same tests can be used for all implementations.
    public Lab1ApplicationTests() {
        WeddingRepository weddingRepository = new MockWeddingRepositoryImpl();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        this.calculateCost = context.getBean(CalculateCost.class);
    }

    @Test
    void calculateCostBeanIsNotNull() {
        assertNotNull(calculateCost, "CalculateCost bean should not be null");
    }

    // Testing if the calculation for the wedding is correct.
    @Test
    void costCalculatesCorrectly() throws Exception {
        assert calculateCost.calculateWeddingCostExVat("RS342") == 920; //VALUE HANGED FROM 360
//        assertEquals(360,calculateCost.calculateWeddingCostExVat("RS342") );
    }

    // Perhaps the wrong ID is provided - is the expected exception thrown?
    @Test
    void costWeddingNotFound_thenThrowsException() throws Exception {
        Exception thrown = assertThrows(
                Exception.class,
                () -> calculateCost.calculateWeddingCostExVat("RS32"),
                "Expected to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("not found"));
    }

    // Note this test only works if VAT is 15%.
    // Recall - bad practise to hardcode the 15% into the code but suffices for this example.
    @Test
    void costWeddingIncVat() throws Exception {
        assert calculateCost.calculateWeddingCostIncVat("RS342") == 414;
    }
}
