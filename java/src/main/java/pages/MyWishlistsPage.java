package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    @FindBy(xpath = "//input[@id='name']")
    private WebElement wishlistField;

    @FindBy(xpath ="//button[@id='submitWishlist']")
    private WebElement submitWishlistButton;

    @FindBy(xpath = "//table[@class='table table-bordered']//tr")
    private WebElement tableBorderRows;

//TODO webelement tabel=//table....

   //methods
    public String getTextPageHeading() {
          return pageHeader.getText();
    }

    public void createNewWishlist(String wishlist) {
        System.out.println("now create the wishlist");
        wishlistField.sendKeys(wishlist);
        submitWishlistButton.click();
    }

    //todo hier (ook) de tabel meegeven
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

    //todo hier (ook) de tabel meegeven
    private int getRowNumber(String wishlist) {
        List<String> rows= createArrayListOfRowsHeaders();
        int rowNumber=0;
        for (int i=0; i < rows.size();i++) {
            if (rows.get(i).equals(wishlist)) {
                rowNumber=i+1;
                //todo break als gevonden
                System.out.println(wishlist + " is in row number:" + rowNumber);
            }
        }
//todo: exception als niet gevonden
        return rowNumber;
    }

    private List<String> createArrayListOfRowsHeaders() {
        //TODO geef de tabel mee, zodat je niet met driver hoeft te werken,
        //TODO dan krijg je dus zoiets:
        //List<table> rows = table.findElements(By.xpath("//[@class='table table-bordered']//tr"));
        //je hebt dan ook geen last van element die er niet meer is in tabel maar wel in dom
        //behalve dat je de tabel elke keer opnieuw moet ophalen als je er een wijziging in aanbrengt (stale)
        //en de bedoeling was om ook door kolommen te itereren

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered']//tr"));
        //count number of rows and print to console
        //List<WebElement> rows = driver.tableBorderRows;
         System.out.println("number of rows in table= " + rows.size());

        //create list with headers and print row headers to console
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
        // driver.navigate().refresh();//dit refreshed niet! (dat doet inderdaad niet)
        // driver.findElement(By.id("module-blockwishlist-mywishlist")).sendKeys(Keys.F5);//werkt ook niet altijd
        //refresh werkt niet dus terug naar my account
        new WebDriverWait(driver, 10).
        until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='icon-chevron-left']"))).click();
    }
}


