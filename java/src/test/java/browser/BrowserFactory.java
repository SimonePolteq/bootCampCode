package browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

    static WebDriver driver;

    public static WebDriver getDriver(String browser) {

        switch(browser.toLowerCase()) {
            case "chrome" :
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;

            case "firefox" :
                driver = getFirefoxDriver();
                 break;

           case "ie" :
            InternetExplorerDriverManager.getInstance().setup();
            driver= new InternetExplorerDriver();
            break;

            default:
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }//end switch

    //in aparte methodes om de switch leesbaar te houden.
    //todo uitbreiden zie de slide 45 (pdf 9)
    private static WebDriver getFirefoxDriver() {
        FirefoxDriverManager.getInstance().setup();
        return driver = new FirefoxDriver();
    }
}
