package chapterNine;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.PageHeaderPage;

//oefening 9.04 (hier submit je niet!)
public class NOK_ContactFormEmailFormatTest extends TestShopScenarioAdvanced {

    @Test
    private void Test1 (){
        PageHeaderPage pageHeader = new PageHeaderPage(driver);
        pageHeader.makeSureNoUserIsLoggedIn();
        pageHeader.goToContactUs();

        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.fillInForm("Customer service","nope", "4321234","Help!");

        // Valideer dat dit format niet correct is
        Assertions.assertThat(contactUsPage.isVisibleFieldError())
                .as("invalid data > format warning for email should be displayed ")
                .isTrue();
    }
}
