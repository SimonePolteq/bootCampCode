package chapterNine;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

public class FillInContactFormTest extends TestShopScenarioAdvanced {

    @Test
    public void fillInContactForm(){

        //ga naar contact
        HomePage homePage = new HomePage(driver);
        homePage.selectContactUs();

        // fill in form & submit
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.submitForm("Customer service","simone.russchen@polteq.com", " 123","no message");
    //de bijlage kan je gewoon een string in zetten

        //Check result
        Assertions.assertThat(contactUsPage.isVisibleSuccessMessage()).as("successmessage Contact Form received is not displayed").isTrue();
    }
}
