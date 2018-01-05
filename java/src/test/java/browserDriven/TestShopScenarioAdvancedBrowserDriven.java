package browserDriven;

import browser.BrowserFactoryAdvanced;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static browser.BrowserFactoryAdvanced.Browsers.CHROME;


public class TestShopScenarioAdvancedBrowserDriven {

    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(BrowserFactoryAdvanced.Browsers browser) { //de parameter is de enum uit de class BrowserFactoryAdvanced
        driver= BrowserFactoryAdvanced.getDriver(browser);

        //open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
