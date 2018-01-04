package chapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TestShopScenario {

    protected WebDriver driver;

    @BeforeTest
    @BeforeMethod
    public void setUp() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        //open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");

        driver.manage().window().maximize();

    }
    @AfterClass
    @AfterMethod
    @AfterTest
    public void tearDown() {
       driver.quit();
    }
}
