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
import java.util.concurrent.TimeUnit;

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

    @FindBy(xpath = "//table[@class='table table-bordered']")
    private WebElement tableWishlists;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement wishlistField;

    @FindBy(xpath ="//button[@id='submitWishlist']")
    private WebElement submitWishlistButton;

    //todo nodig?
    @FindBy(xpath = "//table[@class='table table-bordered']//th")
    private WebElement allColumns;

    //todo nodig?
    @FindBy(xpath = "//table[@class='table table-bordered']//tr")
    private WebElement allRows;

    //methods
    public String getTextPageHeading() {
          return pageHeader.getText();
    }

    public void createNewWishlist(String wishlist) {
        System.out.println("now create the wishlist");
        wishlistField.sendKeys(wishlist);
        submitWishlistButton.click();
    }

    public boolean isPresentWishlist(String wishlist) {
        List<String> rowsList= createArrayListOfRowsHeaders();

        boolean isPresent = false;
             for (int i=0; i < rowsList.size();i++) {
                if (rowsList.get(i).equals(wishlist)) {
                    isPresent = true;
                      //todo break als gevonden
                }
            }
         return isPresent;
    }

    private int getRowNumber(String wishlist) {
        //todo moet ik deze echt steeds opnieuw maken
        List<String> rows= createArrayListOfRowsHeaders();
        int rowNumber=0;
        for (int i=0; i < rows.size();i++) {
            if (rows.get(i).equals(wishlist)) {
                rowNumber=i+1;
                //todo break als gevonden
                System.out.println(wishlist + " is in row number:" + rowNumber);
            }
        }

        return rowNumber;
    }

    private List<String> createArrayListOfRowsHeaders() {
        //todo nog met hiervboven gedefineerde elementen
        //count number of rows and print to console
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered']//tr"));
        System.out.println("number of rows in table= " + rows.size());

        //create list with headers and print row headers to console
        //todo row numbers hier is lelijk
        List<String> rowsHeaders = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            rowsHeaders.add(driver.findElement(By.xpath("//table/tbody//tr[" + i + "]//a")).getText());
            //print tablebody
            //System.out.println("row nr" + i + " = " + rowsHeaders.get(i-1));//because body starts at 2nd row
        }
        return rowsHeaders;
    }


    public void deleteWishlist(String wishlist) {
        //request the rownumber to remove
        int rowNumber = getRowNumber(wishlist);
        System.out.println("delete row:" + rowNumber);

        //delete it
        driver.findElement(By.xpath("//table/tbody/tr[" + rowNumber + "]//i[@class='icon-remove']")).click();

        //accept deletion in  popup
        driver.switchTo().alert().accept();

        //todo dit werkt niet
        //new WebDriverWait(driver, 2).until(getRowNumber(wishlist)==0);
        System.out.println("even 2 secondes wachten");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        System.out.println("2 secondes gewacht");
    }

}


