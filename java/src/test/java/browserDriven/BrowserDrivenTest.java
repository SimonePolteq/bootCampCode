package browserDriven;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import pages.PageHeaderPage;


//Oefening 9.01
public class BrowserDrivenTest extends TestShopScenarioAdvancedBrowserDriven {

    @Test
    public void fillInContactFormTest(){
        PageHeaderPage pageHeader = new PageHeaderPage(driver);
        pageHeader.makeSureNoUserIsLoggedIn();

        //go to Contact Us
        HomePage homePage = new HomePage(driver);
        homePage.selectContactUs();

        // fill in form & submit
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.fillInForm("Customer service","bootcamper@feethepan.com", "4321234","Ipad defect while lifting, need new one");
        contactUsPage.submitForm();

        //Check result
        Assertions.assertThat(contactUsPage.isVisibleSuccessMessage()).as("successmessage Contact Form received is not displayed").isTrue();
    }
}
