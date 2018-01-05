package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class SignOutTest extends TestShopScenario{

    @Test
    //OEFENING 6.0.1
    public void logInLogOutSuccesfull() {

        login("simone.russchen@polteq.com","bootcamp" );
        Assertions.assertThat(driver.findElement(By.cssSelector("[class='header_user_info']")).getText())
                .as("user should be logged in")
                .isNotEqualToIgnoringCase("Sign in");

        //Log uit
        driver.findElement(By.className("logout")).click();

        //Validate that user is no longer logged in
        Assertions.assertThat(driver.findElement(By.cssSelector("[class='header_user_info']")).getText()).
                as("Check Login is visible so user is no longer logged in")
                .isEqualToIgnoringCase("Sign in");
    }

    private void login(String email, String password){
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("SubmitLogin")).click();
    }
}