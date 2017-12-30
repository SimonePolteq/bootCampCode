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

    //elements
    @FindBy(css ="a[title='Contact us']")
    private WebElement contactUsLink;

    @FindBy(xpath = "//i[@class='icon-heart']")
    private WebElement myWishListsIcon;

    //methods
    public void selectContactUs() {
        contactUsLink.click();
    }

    public void selectMyWishLists() {
        myWishListsIcon.click();
    }
}