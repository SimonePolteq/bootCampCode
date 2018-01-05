package exercises;

public class EnumExample {

    public enum BootcampDays{
        DAYONE,
        DAYTWO;
    }

    public static String checkBootcampDaysInfo(BootcampDays bootcampDay){
        //of (BootcampDays bootcampDays) omdat het makkelijker leesbaar is

        switch(bootcampDay) {
            case DAYONE: default:
                return "Kerst was erg pittig";
            case DAYTWO:
                return "Oudjaarsavond was echter lollig";
        }
    }
}
