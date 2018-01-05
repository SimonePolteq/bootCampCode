package chapterNine;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.PageHeaderPage;

//oefening 9.03 (hier submit je wel)
public class NOK_FillInContactFormTest extends TestShopScenarioAdvanced {

    @Test
    private void NOK_FillInContactFormTest(){
        PageHeaderPage pageHeader = new PageHeaderPage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);

        pageHeader.makeSureNoUserIsLoggedIn();
        pageHeader.goToContactUs();

        contactUsPage.fillInForm("Customer service","nope", "4321234","Help!");
        contactUsPage.submitForm();

        // Valideer dat de email niet wordt geaccepteerd
        Assertions.assertThat(contactUsPage.isVisibleErrorMessage()).as("invalid data > errormessage Contact Form should be displayed ").isTrue();
        Assertions.assertThat(contactUsPage.getTextErrorMessage()).as("invalid dat in Contact Us gives incorrect error ").contains("Invalid email address.");
    }
}
