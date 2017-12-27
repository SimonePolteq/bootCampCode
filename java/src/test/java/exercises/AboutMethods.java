package exercises;

import org.testng.annotations.Test;

public class AboutMethods {
    @Test
    public void printProduct() {
        System.out.println(multiply(5,6));
    }

    @Test
    public void printProductTwo() {
        System.out.println(multiplyTwo(3,3));
    }

    private int multiply(int a, int b) {
        return a*b;
    }

    private int multiplyTwo(int numberOne, int numberTwo) {
        return numberOne*numberTwo;
    }
}
