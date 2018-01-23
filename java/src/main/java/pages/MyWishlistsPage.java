package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MyWishlistsPage {

    private WebDriver driver;

    public MyWishlistsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private List<String> rowTableBodyHeaders;

    //elements
    @FindBy(xpath = "//h1[@class='page-heading']")
    private WebElement pageHeader;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement wishlistField;

    @FindBy(xpath ="//button[@id='submitWishlist']")
    private WebElement submitWishlistButton;

    @FindBy(xpath = "//table[@class='table table-bordered']")
    private WebElement wishListTable;

    //methods
    public String getTextPageHeading() {

        return pageHeader.getText();
    }

    public void createNewWishlist(String wishlist) {
        System.out.println("now create the wishlist");
        wishlistField.sendKeys(wishlist);
        submitWishlistButton.click();
    }

    public WebElement getWishListTable() {
        return wishListTable;
    }

    public int getIndexForColumn(String columnTitle) {
        List<WebElement> rowsInTable = wishListTable.findElements(By.tagName("tr"));
        List<WebElement> headerRowInTable = rowsInTable.get(0).findElements(By.tagName("th"));
        //Get the index for a particular column. We achieve this by looping
            // over the List from the headerRowInTable containing the column
            int columnIndex = -1;

            for (int i = 0; i < headerRowInTable.size(); i++) {
                //System.out.println("looking for index column \"" + columnTitle + "\" " + headerRowInTable.get(i).getText());
                if (headerRowInTable.get(i).getText().equals(columnTitle)) {
                    columnIndex = i;
                    System.out.println("index for column " + columnTitle + " = " + columnIndex);
                }
            }

            //TODO Assert that both Name and Delete have been found
            //alleen assert werkt niet op page
            //Assertions.assertThat(columnIndex > -1)
            //        .as("Check if Name and Delete were found in the headerRow")
            //        .isTrue();

            return columnIndex;

            //todo als je iets zoekt dat er niet is krijg je indexOutOfBounds
        }


        public boolean isPresentWishlist(String wishlist) {
            List<WebElement> rowsInTable = wishListTable.findElements(By.tagName("tr"));
            List<WebElement> headerRowInTable = rowsInTable.get(0).findElements(By.tagName("th"));
            int indexForColumnName=getIndexForColumn("Name");
            boolean isPresent = false;

            for (int i=1; i < rowsInTable.size();i++) {
                List<WebElement> wishListColumn = rowsInTable.get(i).findElements(By.tagName("td"));

                if (wishListColumn.get(indexForColumnName).getText().equals(wishlist)){
                    isPresent = true;
                }
            }
            return isPresent;
        }
    }