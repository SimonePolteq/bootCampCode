package chapterNine;

import browser.BrowserFactoryAdvanced;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import static browser.BrowserFactoryAdvanced.Browsers.*;


public class TestShopScenarioAdvanced {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver= BrowserFactoryAdvanced.getDriver(CHROME);

        //driver= BrowserFactoryBasic.getDriver("chrome");
        //open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");
        driver.manage().window().maximize();
        //todo
       // WebDriverWait wait = new WebDriver.Timeouts(driver,10);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
