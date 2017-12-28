package chapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestShopScenario {

    protected WebDriver driver;

    @BeforeTest
    public void initialize() {
        String url = "https://techblog.polteq.com/testshop/index.php";
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        System.out.println(" it is time to quit!");
        //driver.quit();
    }
}
