package exercises;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstSeleniumTest2 {

    WebDriver driver;
    String url = "https://techblog.polteq.com/testshop/index.php";
    @BeforeTest
    private void initialize(){
        ChromeDriverManager.getInstance().setup();
       driver = new ChromeDriver();

        driver.get(url);
    }

    @AfterTest
    private void tearDown() {
       // driver.quit();
    }

    @Test
    public void logInSuccessFull(){
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).sendKeys("simone.russchen@polteq.com");
        driver.findElement(By.id("passwd")).sendKeys("bootcamp");
        driver.findElement(By.id("SubmitLogin")).click();
    }
}
