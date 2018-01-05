package dataDriven;

import chapterNine.TestShopScenarioAdvanced;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import pages.PageHeaderPage;


//Oefening 9.01
public class DataDrivenTest extends TestShopScenarioAdvanced {

    @Parameters({"subject","email","orderID","message"})
    @Test
    public void fillInContactFormTest(String subject, String email, String orderID, String message){
        PageHeaderPage pageHeader = new PageHeaderPage(driver);
        pageHeader.makeSureNoUserIsLoggedIn();

        //go to Contact Us
        HomePage homePage = new HomePage(driver);
        homePage.selectContactUs();

        // fill in form & submit
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.fillInForm(subject,email, orderID,message);
        contactUsPage.submitForm();

        //Check result
        Assertions.assertThat(contactUsPage.isVisibleSuccessMessage()).as("successmessage Contact Form received is not displayed").isTrue();
    }
}
