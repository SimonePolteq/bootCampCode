package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageHeaderPage {

    private WebDriver driver;

    public PageHeaderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //elements
    @FindBy(xpath="//a[@class='login']")
    private WebElement loginLink;

   @FindBy(xpath="//a[@class='logout']")
    private WebElement logoutLink;

    @FindBy(xpath = "//a[@title='Contact us']")
    private WebElement contactUsLink;


    public void makeSureNoUserIsLoggedIn() {
        boolean isLoggedOut = ("Sign in".equalsIgnoreCase(driver.findElement(By.cssSelector("[class='header_user_info']")).getText()));
        if (!isLoggedOut) {
           selectLogout();
        }
    }

    public void selectLogin(){

        loginLink.click();
    }

    public void selectLogout() {

        logoutLink.click();
    }

    public void goToContactUs() {

        contactUsLink.click();
    }
}
