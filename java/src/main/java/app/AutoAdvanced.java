package app;

public class AutoAdvanced {

    private String brand; //dit is een global variabele (mogen in de complete class gebruikt worden), private want niemand mag het veranderen
    int force;
    int rpm;

    public AutoAdvanced(String brand,int force, int rpm) {
        this.brand = brand; // je wilt dat de brand die binnenkomt (na de=)  aan de global variabele gegeven wordt
        this.force = force;
        this.rpm = rpm;
        calculateTorque();
    }

    public void printBrand() {
        System.out.println("Brand name (advanced) is: " + brand);
    }

    private void calculateTorque() {
        //eventueel if statement if rpm>0 om te zorgen dat je niet deelt door 0
        //je kan ook de berekening in de sysout doen
        if (rpm >0) {
            double torque = (force * 5252) / rpm;
            System.out.println("De torque (advanced) is: " + torque + " bij force=" + force + " en rpm=" + rpm);
        }
        else {
            System.out.println("sorry, rpm moet groter dan 0 zijn");
        }
    }
}
