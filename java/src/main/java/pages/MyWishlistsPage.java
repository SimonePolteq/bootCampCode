package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MyWishlistsPage {

    private WebDriver driver;

    public MyWishlistsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //todo weghalen of anders maken
        createLists();
    }

    private List<String> rowTableBodyHeaders;

    //elements

    @FindBy(xpath = "//h1[@class='page-heading']")
    private WebElement pageHeader;

    @FindBy(xpath = "//table[@class='table table-bordered']")
    private WebElement tableWishlists;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement wishlistField;

    @FindBy(xpath ="//button[@id='submitWishlist']")
    private WebElement submitWishlistButton;

    //todo nodig?
    @FindBy(xpath = "//table[@class='table table-bordered']/tbody/th")
    private WebElement column;

    //todo nodig?
    @FindBy(xpath = "//table[@class='table table-bordered']/tbody/tr")
    private WebElement row;

    //methods
    public String getTextPageHeading() {
          return pageHeader.getText();
    }

    public void createWishlist(String wishlist) {
        System.out.println("now create the wishlist");
        wishlistField.sendKeys(wishlist);
        submitWishlistButton.click();
    }

    public boolean isPresentWishlist(String wishlist) {
        //todo
        // todo gebruik WebTables voor zoeken
        boolean isPresent = false;
            for (int i=0; i < rowTableBodyHeaders.size();i++) {
                if (rowTableBodyHeaders.get(i).equals(wishlist)) {
                    isPresent = true;
                    //todo break als gevonden
                }
            }//end for statement
        return isPresent;
    }

    public void deleteWishlist(String wishList) {
        //todo
        System.out.println("de code voor deleting wishlist moet nog worden geschreven");
    }


    private void createLists() {
        //count number of rows and print to console
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered']//tr"));
        System.out.println("number of rows in table= " + rows.size());

        //count number of colums and print to console
        List<WebElement> columns = driver.findElements(By.xpath("//table[@class='table table-bordered']//th"));
        System.out.println("numbers of colums in table=" + columns.size());

        //omdat ik het nog even niet weet hoe direct te extracten maak ik lijst met de strings
        //create list and print column headers to console
        List<String> columnHeaders = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            String xpath = "//table//th[" + (i + 1) + "]";
            columnHeaders.add(driver.findElement(By.xpath(xpath)).getText());
            //print
            System.out.println("column nr" + (i + 1) + " = " + columnHeaders.get(i));
        }

        //create list and print row headers to console
        rowTableBodyHeaders = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            String xpath = "//table/tbody//tr[" + i + "]//a";
            rowTableBodyHeaders.add(driver.findElement(By.xpath(xpath)).getText());
            //print tablebody
            System.out.println("row nr" + i + " = " + rowTableBodyHeaders.get(i-1));//because body starts at 2nd row
        }
    }
}
