package browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactoryMedior
{


    public static WebDriver getDriver(String browser) {
        WebDriver driver;
        switch(browser.toLowerCase()) {
            case "chrome" :
                return createChromeBrowser();
            case "firefox" :
                return createFirefoxDriver();
            case "ie" :
                return createIeBrowser();
            case "edge":
                return createEdgeBrowser();
            default:
                return createChromeBrowser();
        }//end switch
    }


    //methodes apart om switch leesbaar te houden
    private static WebDriver createChromeBrowser() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        //Chrome options are chrome specific
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("ignore-certificate-errors");
        //Capabilities can be used for WebDriver capabilities ie: proxy
        capabilities.setCapability("chrome.switches", "--verbose");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriverManager.getInstance().setup();
        return new ChromeDriver(capabilities);

    }
    private static WebDriver createEdgeBrowser() {
        EdgeDriverManager.getInstance().setup();
        return new EdgeDriver();
        }

    private static WebDriver createIeBrowser() {
                InternetExplorerDriverManager.getInstance().setup();
      return new InternetExplorerDriver();
       }

    private static WebDriver createFirefoxDriver() {
        FirefoxDriverManager.getInstance().setup();
        return new FirefoxDriver();
    }
}
