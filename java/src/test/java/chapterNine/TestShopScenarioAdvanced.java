package chapterNine;

import browser.BrowserFactoryBasic;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestShopScenarioAdvanced {

    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver= BrowserFactoryBasic.getDriver("chrome");

        //open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
       driver.quit();
    }
}
