package chapterNine;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.PageHeaderPage;

public class NOK_LogInTest extends TestShopScenarioAdvanced {

    private String incorrectEmail;
    private String incorrectPassword;
    private String CORRECT_EMAIL= "bootcamper@feelthepain.com";
    private String CORRECT_PASSWORD = "1qazxsw2";

    @Test
    private void emailEmpty() {
        incorrectEmail = "";

        PageHeaderPage pageHeader = new PageHeaderPage(driver);
        pageHeader.makeSureNoUserIsLoggedIn();

        pageHeader.selectLogin();
        LogInPage logInPage = new LogInPage(driver);
        logInPage.login(incorrectEmail, CORRECT_PASSWORD);

        WebElement alertMessageElement = new WebDriverWait(driver, 10).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-danger']")));
        Assertions.assertThat(alertMessageElement.isDisplayed()).as("there should be an error on failed login").isTrue();
        Assertions.assertThat(logInPage.getTextError()).as("Text comparison error").isEqualTo("An email address required.");
    }

    @Test
    private void passwordEmpty() {
        incorrectPassword = "";

        PageHeaderPage pageHeader = new PageHeaderPage(driver);
        pageHeader.makeSureNoUserIsLoggedIn();

        pageHeader.selectLogin();
        LogInPage logInPage = new LogInPage(driver);
        logInPage.login(CORRECT_EMAIL, incorrectPassword);
        WebElement alertMessageElement = new WebDriverWait(driver, 10).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-danger']")));

        Assertions.assertThat(alertMessageElement.isDisplayed()).as("there should be an error on failed login").isTrue();
    }

    @Test
    private void emailFormatIncorrect() {
        incorrectEmail = "bootcamper";

        PageHeaderPage pageHeader = new PageHeaderPage(driver);
        pageHeader.makeSureNoUserIsLoggedIn();

        pageHeader.selectLogin();
        LogInPage logInPage = new LogInPage(driver);
        logInPage.login(incorrectEmail, CORRECT_PASSWORD);

        //CAUTION this is another assert as in the 3 other tests!
        Assertions.assertThat(logInPage.isVisibleFieldError())
                .as("there should be an error at the emailfield")
                .isTrue();
    }
}