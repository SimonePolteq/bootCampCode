package chapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestShopScenario {

    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        //open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
       // driver.quit();
    }
}
