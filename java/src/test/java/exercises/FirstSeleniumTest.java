package exercises;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstSeleniumTest {

    @Test
    public void logInSuccessFull(){
        //open the website
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://techblog.polteq.com/testshop/index.php");

        //Click on the login link
        driver.findElement(By.className("login")).click();

        //enter username and password
        driver.findElement(By.id("email")).sendKeys("simone.russchen@polteq.com");
        driver.findElement(By.id("passwd")).sendKeys("bootcamp");

        //Click login button
        driver.findElement(By.id("SubmitLogin")).click();

        //validate result
        //deze is niet uniek voor de " my account" pagina en dat was wel de bedoeling
        //boolean logoutIsPresent = driver.findElement(By.className("logout")).isDisplayed();
        //Assertions.assertThat(logoutIsPresent).as("logout element not found").isTrue();

        //dus check op page heading
        WebElement pageHeading=driver.findElement(By.className("page-heading"));  //element als variabele en hier kan je dus de tekst van opvragen
        String pageHeaderTextActual=pageHeading.getText();

        String pageHeaderTextExpected="My account";
        Assertions.assertThat(pageHeaderTextActual).as("Check visbility of My Account Page").isEqualToIgnoringCase(pageHeaderTextExpected); //h1 wordt hoofdletters does ignore case

        //close the browser
        driver.quit();
    }
}
