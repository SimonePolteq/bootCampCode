package exercises;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ConditionExercises {

    private List<Integer> testData;

    @Test
    private void checkOnAge() {

        createListWithAges();

        //check if participant is old enough and not to old
        for(int i=0; i< testData.size(); i++) {
            System.out.println("test with age= " +testData.get(i));
            String tekst=bootcampAgeChecker(testData.get(i));
            System.out.println(
tekst);
        }
    }
    private String bootcampAgeChecker(int age) {
        String outputText;
        if (age>=21 && age<65){
            outputText="deelnemer toegelaten";
        }
        else if (age>=65){
            outputText="deelnemer niet toegelaten, check bij de manager";
        }
        else {
            outputText="deelnemer is te jong";
        }
        return outputText;
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
