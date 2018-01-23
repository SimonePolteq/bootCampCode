package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {

    private WebDriver driver;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
        // This call sets the WebElement fields.
        PageFactory.initElements(driver, this);
    }

    //elements
    @FindBy (xpath = "//input[@id='id_gender1']")
    private WebElement selectBoxMr;

    @FindBy (xpath = "//input[@id='id_gender2']")
    private WebElement selectBoxMs;

    @FindBy (xpath = "//input[@id='id_gender3']")
    private WebElement selectBoxMiss;

    @FindBy (xpath = "//input[@id='customer_firstname']")
    private WebElement fieldFirstName;

    @FindBy (xpath = "//input[@id='customer_lastname']")
    private WebElement fieldLastName;

    @FindBy (xpath = "//input[@id='passwd']")
    private WebElement fieldPassword;

    @FindBy (xpath = "//button[@name='submitAccount']")
    private WebElement buttonRegister;



    public void fillInForm(String firstName, String lastName, String password) {
        selectBoxMs.click();
        fieldFirstName.sendKeys(firstName);
        fieldLastName.sendKeys(lastName);
        fieldPassword.sendKeys(password);
        buttonRegister.click();
    }


}
