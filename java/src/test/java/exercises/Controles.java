package exercises;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class Controles {

    @Test
    public void assertStringContains(){
       String textExpected = "this Is An Exercise";
       Assertions.assertThat(textExpected).as("this is the text shown when test fails").contains("Exercise");
    }

    @Test
    public void checkTekstFails(){
        String textExpected = "this Is An Exercise";
        Assertions.assertThat(textExpected).as("Text comparison error").contains("boe");
    }

    @Test
    public void assertBoolean() {
        boolean testBoolean=true;
        Assertions.assertThat(testBoolean).as("selection is false").isFalse();
    }

    @Test
    public void checkNumberIsSmallerFails() {
        int testValue = 10;
        Assertions.assertThat(testValue).as("Waarde is niet kleiner").isLessThan(9);
    }

    @Test
    public void checkNumberIsSmallerPass() {
        int testValue = 10;
        Assertions.assertThat(testValue).as("Waarde is niet kleiner").isLessThan(12);
    }
}
