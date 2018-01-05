package exercises;

public class Auto {
//deze blauwdruk hoort normaal niet in de excercise map, maar voor de excercise wel

    public void printBrand(String brand) {
        System.out.println("Brand name is: " + brand);
    }

    public void printDoors(int numberOfDoors) {
        System.out.println("Aantal deuren is: " + numberOfDoors);
    }

    public void printMotorType(String motorType) {
        System.out.println("Het motortype is: " + motorType);
    }

    public void calculateTorque(int force, int rpm) {
        //eventueel if statement if rpm>0 om te zorgen dat je niet deelt door 0
        //je kan ook de berekening in de sysout doen
        double torque = (force * 5252) / rpm ;
        System.out.println("De torque is: " + torque + " bij force=" + force + " en rpm="+rpm);
    }
}
