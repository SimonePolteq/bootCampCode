package browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactoryBasic {
    static WebDriver driver;

     public static WebDriver getDriver(String browser) {

         switch(browser.toLowerCase()) {
            case "chrome" :
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;

             case "firefox" :
                 FirefoxDriverManager.getInstance().setup();
                driver = new FirefoxDriver();
                 break;

           case "ie" :
            InternetExplorerDriverManager.getInstance().setup();
            driver= new InternetExplorerDriver();
            break;
             case "edge":
                 EdgeDriverManager.getInstance().setup();
                 driver = new EdgeDriver();
                 break;

            default:
                driver = new ChromeDriver();
        }//end switch

        return driver;
    }

    //in aparte methodes om de switch leesbaar te houden.
    //todo uitbreiden zie de slide 45 (pdf 9)
    private static WebDriver getFirefoxDriver() {
        FirefoxDriverManager.getInstance().setup();
        return driver = new FirefoxDriver();
    }
}
