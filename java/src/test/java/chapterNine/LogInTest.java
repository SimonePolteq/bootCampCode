package chapterNine;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.PageHeaderPage;

//oefening 9.02
public class LogInTest extends TestShopScenarioAdvanced {

    @Test
    private void LogInTest() {
        //check that no user is already logged in
        PageHeaderPage pageHeader = new PageHeaderPage(driver);
        pageHeader.makeSureNoUserIsLoggedIn();

        //login
        pageHeader.selectLogin();
        LogInPage logInPage = new LogInPage(driver);
        logInPage.login("bootcamper@feelthepain.com", "1qazxsw2");


        //validate that user is indeed logged in
        Assertions.assertThat(driver.findElement(By.cssSelector("[class='header_user_info']")).getText())
                .as("user should be logged in")
                .isNotEqualToIgnoringCase("Sign in");
    }
}
