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
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactoryMedior
{
    public static WebDriver getDriver(String browser) {
        switch(browser.toLowerCase()) {
            case "chrome": default:
                return getChromeBrowser();
            case "firefox" :
                return getFirefoxDriver();
            case "ie" :
                return getIeBrowser();
        }
    }

    //methodes apart om switch leesbaar te houden
    private static WebDriver getChromeBrowser() {

        // /Chrome options are chrome specific
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("ignore-certificate-errors");

        //Capabilities can be used for WebDriver capabilities ie: proxy
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY,options);
        capabilities.setCapability("chrome.switches", "--verbose");

        ChromeDriverManager.getInstance().setup();

        return new ChromeDriver(capabilities);//deprecated, gebruik options
        //return new ChromeDriver(options);
    }

    private static WebDriver getIeBrowser() {
        InternetExplorerDriverManager.getInstance().setup();
      return new InternetExplorerDriver();
       }

    private static WebDriver getFirefoxDriver() {
       // DesiredCapabilities capabilities = DesiredCapabilities.firefox(); //deprecated
        FirefoxOptions options = new FirefoxOptions();

        FirefoxDriverManager.getInstance().setup();

        //return new FirefoxDriver(capabilities);//deprecated
        return new FirefoxDriver(options);
    }
}


/*
ie

		System.out.println("launching IE browser");
		System.setProperty("webdriver.ie.driver", driverPath+"IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
 */