package chapterSix;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class SignOutTest extends TestShopScenario{

    @Test
    //OEFENING 6.0.1
    public void logInLogOutSuccesfull() {

        login("simone.russchen@polteq.com","bootcamp" );

        //TODO test op de tekst van header_user_info want dat is er altijd, bij ingelogd zelfs 2x
        //Check that user is indeedlogged in]

        boolean isDisplayed = driver.findElement(By.cssSelector("[class='header_user_info']")).isDisplayed();
        System.out.println(isDisplayed);
        //String text =driver.findElement(By.cssSelector("[class='header_user_info']")).getAttribute("title");
        //WAAROM is attribute leeg terwijl ik Log ne out verwacht of log in nogwat?
        String text =driver.findElement(By.cssSelector("[class='header_user_info']")).getText();
        //dit geeft mijn naam terug
        System.out.println("de attribute is" + text);





        boolean isLoggedIn=driver.findElement(By.className("logout")).isDisplayed();
        Assertions.assertThat(isLoggedIn).as("user should be logged in").isTrue();

        //Log uit
        driver.findElement(By.className("logout")).click();

        //Validate that user is no longer logged in
        Assertions.assertThat(driver.findElement(By.className("login")).isDisplayed()).as("Check Login is visible so user is no longer logged in").isTrue();
    }

    private void login(String email, String password){
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("SubmitLogin")).click();
    }
}