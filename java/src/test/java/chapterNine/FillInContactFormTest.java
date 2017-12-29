package chapterNine;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

public class FillInContactFormTest {

    @Test
    public void fillInContactForm(){
        //setup (lineair)
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://techblog.polteq.com/testshop/index.php");
        driver.manage().window().maximize();

        //ga naar contact
        HomePage homePage = new HomePage(driver);
        homePage.selectContactUs();

        // fill in form & submit
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.submitForm("Customer service","simone.russchen@polteq.com", " 123","no message");
//todo de bijlage kan je gewoon een string in zetten

        //Check result
        Assertions.assertThat(contactUsPage.isVisibleSuccessMessage()).as("successmessage Contact Form received is not displayed").isTrue();
    }
}
