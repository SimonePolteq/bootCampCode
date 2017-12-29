package exercises;

import app.AutoAdvanced;
import org.testng.annotations.Test;

public class CarGame {

    @Test
    public void checkCar() {
        Auto auto = new Auto();
        auto.printBrand("Volvo");
        auto.printDoors(5);
        auto.printMotorType("Diesel");
        auto.calculateTorque(16,150);
    }

    @Test
    public void checkBetterCar() {
        AutoAdvanced autoAdvanced = new AutoAdvanced("Tesla",10, 200);
        autoAdvanced.printBrand();
    }
}
