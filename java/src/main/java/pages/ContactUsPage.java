package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {


    private WebDriver driver;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Onder suppliers, verander de selectbox naar <AppleStore>
    @FindBy(css = "select[name=id_contact]")
    private WebElement subjectHeading;

    //@FindBy(css = "input[id=email]")
    //@FindBy(id="email")
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    @FindBy(css = " input[id=id_order]")
    private WebElement orderReferenceField;

    @FindBy(css = "textarea[id=message]")
    private WebElement messageField;

    @FindBy(css = "button[id=submitMessage]")
    private WebElement submitButton;

    @FindBy(className = "alert-success")
    private WebElement succesMessage;

    public void submitForm(String subject, String email, String orderReference, String message) {
        Select dropdown = new Select(subjectHeading);
        dropdown.selectByVisibleText(subject);

        //of: new Select(subjectHeading).selectByVisibleText(subject);

        emailField.sendKeys(email);
        orderReferenceField.sendKeys(orderReference);
        messageField.sendKeys(message);
        submitButton.click();
    }

    public boolean isVisibleSuccessMessage() {

        //werkt niet: return new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated( succesMessage.isDisplayed() ));
        return succesMessage.isDisplayed();
    }
}
