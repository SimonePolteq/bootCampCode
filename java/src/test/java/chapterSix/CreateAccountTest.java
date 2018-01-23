package chapterSix;

import chapterNine.TestShopScenarioAdvanced;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.CreateAccountPage;
import pages.LogInPage;
import pages.PageHeaderPage;

import java.util.UUID;

public class CreateAccountTest extends TestShopScenarioAdvanced {

    @Test
    private void createAccount() {
        String email = UUID.randomUUID() + "@foobar.com";
        email = email.replace("-", "");

        String firstName = "Simone";
        String lastName = "Tester";
        String password = "test123";
        WebDriverWait wait = new WebDriverWait(driver,20);

        //check that no user is already logged in
        PageHeaderPage pageHeader = new PageHeaderPage(driver);
        pageHeader.makeSureNoUserIsLoggedIn();

        //create account can be found login
        pageHeader.selectLogin();
        LogInPage logInPage = new LogInPage(driver);
        logInPage.createAccount(email);

        //wait for page is loaded
        wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender2"))).click();
        //validate that user is on page "create an account"i
        Assertions.assertThat(driver.findElement(By.xpath("//h1[@class='page-heading']")).getText())
                .as("user should be on Create An Account Page")
                .isEqualToIgnoringCase("Create an account");

        CreateAccountPage createAccountPage= new CreateAccountPage(driver);
        createAccountPage.fillInForm(firstName,lastName, password);

        //verify that account was indeed created
        Assertions.assertThat(driver.findElement(By.cssSelector("a.account > span")).getText())
                .as("Username in header").isEqualTo(firstName + " " + lastName);
        Assertions.assertThat(driver.findElement(By.cssSelector("p.alert.alert-success")).getText())
                .as("Message that I have created an account").isEqualTo("Your account has been created.");
        Assertions.assertThat(driver.findElement(By.tagName("h1")).getText())
                .as("Page Title of My Account Page").isEqualTo("MY ACCOUNT");
        Assertions.assertThat(driver.getTitle())
                .as("Browser title").contains("My account");

    }
}
