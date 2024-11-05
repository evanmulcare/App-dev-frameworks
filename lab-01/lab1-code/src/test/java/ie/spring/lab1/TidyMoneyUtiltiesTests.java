package ie.spring.lab1;

import ie.spring.lab1.services.utilities.TidyMoney;
import org.junit.jupiter.api.Test;

public class TidyMoneyUtiltiesTests {

    @Test
    public void twoDigitsRoundDown(){
        assert TidyMoney.twoDigits(3.501) == 3.50;
    }

    @Test
    public void twoDigitsRoundUp(){
        assert TidyMoney.twoDigits(3.507) == 3.51;
    }

    @Test
    public void twoDigitsExact(){
        assert TidyMoney.twoDigits(3.50) == 3.5;
    }

    @Test
    public void twoDigitsHalfWay(){
        assert TidyMoney.twoDigits(3.505) == 3.51;
    }

}
