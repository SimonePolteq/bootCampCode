package chapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FillCartTest {

    WebDriver driver;

    @BeforeTest
    private void initialize() {
        String url = "https://techblog.polteq.com/testshop/index.php";
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    private void tearDown() {
        driver.quit();
    }

    private void login() {
        //TODO met parameters username en pw
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).sendKeys("simone.russchen@polteq.com");
        driver.findElement(By.id("passwd")).sendKeys("bootcamp");
        driver.findElement(By.id("SubmitLogin")).click();
    }


    @Test
    public void FillCart() {
        login();

        //Valider dat cart empty is (tip: gebruik .isDisplayed(), empty is een element opzich)

        //4. Click op <ipod> bij het onderdeel TAGS (Tip: Bouw een CSS locator aan de hand van een attribuut)

        // 5. Click op de naam van <iPod shuffle> zodat de product pagina wordt geopened (Tip: bouw een xPath locator aan de hand van de tekst van het element)

        // 6. Voeg de <iPod Shuffle> toe aan de cart (Tip: na een paar keer vastlopen, kijk hoger in de DOM)

        // 7. Click op de knop continue shopping (tip: title als locator, idem als stap 4, selenium is sneller dan de site wellicht?)

        //  8. Valideer dat er nu 1 product zich in de cart bevindt (tip: nummer is een element opzich)
    }
}

