package homework;

import chapterNine.TestShopScenarioAdvanced;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.PageHeaderPage;
import pages.HomePage;
import pages.MyWishlistsPage;

import java.util.List;

public class DeleteWishListTest extends TestShopScenarioAdvanced {

    @Test
    public void deleteWishListTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //declaration
        HomePage homePage = new HomePage(driver);
        MyWishlistsPage myWishlistsPage = new MyWishlistsPage(driver);
        PageHeaderPage pageHeaderPage = new PageHeaderPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        String wishlist = "Testje"; //"Feel the pain";

        //login and check we are indeed logged in
        pageHeaderPage.selectLogin();
        logInPage.login("simone@russchen.com", "1qazxsw2");
        Assertions.assertThat(driver.findElement(By.cssSelector("[class='header_user_info']")).getText())
                .as("user should be logged in")
                .isNotEqualToIgnoringCase("Sign in");

        //go to My wishlists & check we are there
        homePage.selectMyWishLists();

        Assertions.assertThat(myWishlistsPage.getTextPageHeading())
                .as("Should be on My Wishlists page")
                .isEqualToIgnoringCase("My wishlists");

        //Check if wishlist is available, otherwise create
        System.out.println("check of wishlist er al is");
        if (myWishlistsPage.isPresentWishlist(wishlist)) {
            System.out.println("wishlist " + wishlist + " is already available, so no need to create");
        }
        else {
            System.out.println("wishlist " + wishlist + " unavailable, so create it");
            myWishlistsPage.createNewWishlist(wishlist);
            Assertions.assertThat(myWishlistsPage.isPresentWishlist(wishlist)).as("Wishlist " + wishlist + "should have been added").isTrue();
        }


        //get the table
        WebElement wishListTable = myWishlistsPage.getWishListTable();

        //rows in table
            List<WebElement> rowsInTable = wishListTable.findElements(By.tagName("tr"));
            System.out.println("number of rows in table=" + rowsInTable.size());

            //columns in table
            List<WebElement> headerRowInTable = rowsInTable.get(0).findElements(By.tagName("th"));
            System.out.println("number of columns in table=" + headerRowInTable.size());

            //request the index of the columns "name" and "delete"
            int indexForColumnDelete=myWishlistsPage.getIndexForColumn("Delete" );
            int indexForColumnName=myWishlistsPage.getIndexForColumn("Name");

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

          wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(wishListTable, wishlist)));
            wishListTable = driver.findElement(By.cssSelector(".table.table-bordered"));
            rowsInTable = wishListTable.findElements(By.tagName("tr"));


        //Check deletion was succesfull
        System.out.println("Check if deletion was succesfull");
        Assertions.assertThat(myWishlistsPage.isPresentWishlist(wishlist)).
                as("Wishlist " + wishlist + " should have been deleted").
                isFalse();

        //Add the wishlist again so the code is 100% re-runable without any code or function adjustments
        System.out.println("add the wishlist again at end of test");
        myWishlistsPage.createNewWishlist(wishlist);
        Assertions.assertThat(myWishlistsPage.isPresentWishlist(wishlist)).as("Wishlist " + wishlist + "should have been added at end of test").isTrue();
    }
}