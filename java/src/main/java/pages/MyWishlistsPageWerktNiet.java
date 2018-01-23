package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MyWishlistsPageWerktNiet {

    private WebDriver driver;

    public MyWishlistsPageWerktNiet(WebDriver driver) {
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

    public void deleteWishlist(String wishlist) {
       //rows in table
        List<WebElement> rowsInTable = wishListTable.findElements(By.tagName("tr"));
        System.out.println("number of rows in table=" + rowsInTable.size());

        //columns in table
        List<WebElement> headerRowInTable = rowsInTable.get(0).findElements(By.tagName("th"));
        System.out.println("number of columns in table=" + headerRowInTable.size());

        //request the index of the columns "name" and "delete"
        int indexForColumnDelete=getIndexForColumn("Delete" , rowsInTable, headerRowInTable);
        int indexForColumnName=getIndexForColumn("Name", rowsInTable, headerRowInTable);

        //Find the correct row which contains the wishlist we are looking for
        //        SKIP HEADER ROW (which has index = 0)
        for (int i = 1; i < rowsInTable.size(); i++) {
            //Grab one of the rows in the webtable by using the index (i), get all the columns
            //and place them in a new List. Now listColumnsInRow represents all the columns in row(i)
            List<WebElement> listColumnsInRow = rowsInTable.get(i).findElements(By.tagName("td"));

            //print content of row
            /*
            for(WebElement webElement : listColumnsInRow ) {
                System.out.println(webElement.getText());
            }
            */
                // Since we know the index of column "Name" which holds the name of the wishlist we can
                //  directly get that column number and check the contents of this cell
                if (listColumnsInRow.get(indexForColumnName).getText().equals(wishlist)) {

                    // Now we can click the delete icon, again a column number we already know
                    listColumnsInRow.get(indexForColumnDelete).findElement(By.cssSelector("a.icon")).click();
                    //We break the loop otherwise java continues but the website is trying to process the delete
                    break;
                }
            }


            //accept deletion in  popup
            driver.switchTo().alert().accept();


            //Refresh the WebTable, element has become stale meaning the webtable in memory of the code
            //  is not the same anymore as the webtable on the site (- 1 wishlist)
            //      We wait for the "wishlist" text to be gone by inverting the TextToPresentInElement

       // wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(wishListTable, wishlist)));
        //wishListTable = driver.findElement(By.cssSelector(".table.table-bordered"));
        //rowsInTable = wishListTable.findElements(By.tagName("tr"));



    }

        public int getIndexForColumn(String columnTitle, List<WebElement> rowsInTable, List<WebElement> headerRowInTable) {
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


            //alleen assert werkt niet op page
            //Assertions.assertThat(columnIndex > -1)
            //        .as("Check if Name and Delete were found in the headerRow")
            //        .isTrue();

            return columnIndex;

            //als je iets zoekt dat er niet is krijg je indexOutOfBounds
        }


        public boolean isPresentWishlist(String wishlist) {
            List<WebElement> rowsInTable = wishListTable.findElements(By.tagName("tr"));
            List<WebElement> headerRowInTable = rowsInTable.get(0).findElements(By.tagName("th"));
            int indexForColumnName=getIndexForColumn("Name", rowsInTable, headerRowInTable);
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