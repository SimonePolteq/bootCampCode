package browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class BrowserFactoryAdvanced {


    public enum Browsers {
        CHROME,
        FIREFOX;
    }

    public static WebDriver getDriver(Browsers browser) {
        switch(browser) {
            case CHROME: default:
                return getChromeBrowser();
            case FIREFOX :
                return getFirefoxDriver();
        }
    }

    private static WebDriver getChromeBrowser() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("ignore-certificate-errors");
        ChromeDriverManager.getInstance().setup();
        return new ChromeDriver(options);
    }

    private static WebDriver getFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxDriverManager.getInstance().setup();
        return new FirefoxDriver(options);
    }
}
