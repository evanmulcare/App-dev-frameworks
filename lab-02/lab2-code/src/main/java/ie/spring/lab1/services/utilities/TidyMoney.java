package ie.spring.lab1.services.utilities;
public class TidyMoney {

    public static double twoDigits(double amount){
        return Math.round(amount * 100 ) / 100.0;
    }

}

