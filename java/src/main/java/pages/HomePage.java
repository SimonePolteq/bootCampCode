package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //elementen
    @FindBy(css ="a[title='Contact us']")
    private WebElement contactUsLink;


    //methodes
    public void selectContactUs() {
        contactUsLink.click();
    }
}