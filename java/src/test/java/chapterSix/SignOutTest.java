package chapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignOutTest extends TestShopScenario{

    @Test
    public void logInLogOutSuccesfull() {
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).sendKeys("simone.russchen@polteq.com");
        driver.findElement(By.id("passwd")).sendKeys("bootcamp");
        driver.findElement(By.id("SubmitLogin")).click();

        //check the page heading is MY ACCOUNT
        String pageHeaderTextActual = driver.findElement(By.className("page-heading")).getText();
        String pageHeaderTextExpected = "My account";
        Assertions.assertThat(pageHeaderTextActual).as("Check visbility of My Account Page").isEqualToIgnoringCase(pageHeaderTextExpected); //h1 wordt hoofdletter

        //Check logged in
        //TODO test op de tekst van header_user_info want dat is er altijd, buj ingelogd zelfs 2x


        //Log uit
        driver.findElement(By.className("logout")).click();

        //Validate that user is no longer logged in
        Assertions.assertThat(driver.findElement(By.className("login")).isDisplayed()).as("Check Login is visible so user is no longer logged in").isTrue();
    }
}