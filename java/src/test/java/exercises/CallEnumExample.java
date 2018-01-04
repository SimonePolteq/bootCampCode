package exercises;

import org.testng.annotations.Test;

public class CallEnumExample {

    @Test
    public void printBootcampDays(){
        System.out.println(EnumExample.checkBootcampDaysInfo(EnumExample.BootcampDays.DAYONE));
        //je kan deel weghalen en importeren
        System.out.println(EnumExample.checkBootcampDaysInfo(EnumExample.BootcampDays.DAYTWO));
    }
}
