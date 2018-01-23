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
        // This call sets the WebElement fields.
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "select[name=id_contact]")
    private WebElement subjectHeading;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    //warning: if logged in this field is a dropdown and thus another element!
    @FindBy(xpath = "//input[@name='id_order']")
    private WebElement orderReferenceField;

    @FindBy(css = "textarea[id=message]")
    private WebElement messageField;

    @FindBy(css = "button[id=submitMessage]")
    private WebElement submitButton;

    @FindBy(className = "alert-success")
    private WebElement succesMessage;

    @FindBy(xpath = "//div[@class='alert alert-danger']")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@class='alert alert-danger']//li")
    private WebElement errorText;

    @FindBy(xpath = "//p[@class='form-group form-error']")
    private WebElement fieldErrorEmail;


    //METHODS
    public void fillInAndsubmitForm(String subject, String email, String orderReference, String message) {
        fillInForm(subject, email, orderReference, message);
        submitButton.click();
    }

    public void submitForm() {
        submitButton.click();
    }

    //de bijlage kan je gewoon een string in zetten
    public void fillInForm(String subject, String email, String orderReference, String message) {
        clearFields();
        new Select(subjectHeading).selectByVisibleText(subject);
        emailField.sendKeys(email);
        orderReferenceField.sendKeys(orderReference);
        messageField.sendKeys(message);
    }

    public boolean isVisibleSuccessMessage() {

        return succesMessage.isDisplayed();
    }

    public boolean isVisibleErrorMessage() {

        return errorMessage.isDisplayed();
    }

    public String getTextErrorMessage() {
        return errorText.getText();
    }

    public void clearFields() {
        emailField.clear();
        orderReferenceField.clear();
        messageField.clear();
    }


    public boolean isVisibleFieldError() {

        return fieldErrorEmail.isDisplayed();
    }
}
