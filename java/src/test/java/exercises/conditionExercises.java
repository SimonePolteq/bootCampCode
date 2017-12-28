package exercises;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class conditionExercises {

    private List<Integer> testData;

    @Test
    private void checkOnAge() {

        createListWithAges();

        //check if participant is old enough and not to old
        for(int i=0; i< testData.size(); i++) {
            System.out.println("test met age= " +testData.get(i));
            bootcampAgeChecker(testData.get(i));
        }
    }

    private void bootcampAgeChecker(int age) {

        if (age>=21 && age<65){
            System.out.println("    deelnemer toegelaten");
        }
        else if (age>=65){
            System.out.println("    deelnemer niet toegelaten, check bij de manager");
        }
        else {
            System.out.println("    deelnemer is te jong");
        }
    }

    private void createListWithAges() {
        //create the array
        testData = new ArrayList<>();

        //fill the list with ages we like to check
        testData.add(19);
        testData.add(21);
        testData.add(24);
        testData.add(64);
        testData.add(65);
    }
}
