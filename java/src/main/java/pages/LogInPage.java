package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class LogInPage {

private WebDriver driver;

public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        }

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField ;

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']")
    private WebElement alertMessage;

    @FindBy(xpath = "//div[@class='alert alert-danger']//li")
    private WebElement alertMessageText;
    //todo check if specifiek genoeg

    @FindBy(xpath = "//div[@class='form-group form-error']")
    private WebElement fieldError;


//todo refcator name
    public void login(String email, String password) {

        fillInLoginFields(email, password);
        signInButton.click();
    }

    public void fillInLoginFields(String email, String password){

        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    public String getTextError(){

        return alertMessageText.getText();
    }

    public boolean isVisibleFieldError() {

        return fieldError.isDisplayed();
    }
}

