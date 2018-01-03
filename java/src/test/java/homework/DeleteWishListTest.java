package homework;

import chapterNine.TestShopScenarioAdvanced;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.PageHeaderPage;
import pages.HomePage;
import pages.MyWishlistsPage;

public class DeleteWishListTest extends TestShopScenarioAdvanced {

    @Test
    public void deleteWishListTest() {

        HomePage homePage = new HomePage(driver);
        MyWishlistsPage myWishlistsPage = new MyWishlistsPage(driver);
        PageHeaderPage headAllPagesPage = new PageHeaderPage(driver);

        String wishlist = "Feel the pain";

        //login and check we are indeed logged in
        headAllPagesPage.login("simone@russchen.com", "1qazxsw2");
        Assertions.assertThat(driver.findElement(By.cssSelector("[class='header_user_info']")).getText())
                .as("user should be logged in")
                .isNotEqualToIgnoringCase("Sign in");

        //ga naar My wishlists & check we are there
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

        //Delete wishlist
        myWishlistsPage.deleteWishlist(wishlist);


        homePage.selectMyWishLists();



        //Check deletion was succesfull
        System.out.println("en, gelukt?");
       Assertions.assertThat(myWishlistsPage.isPresentWishlist(wishlist)).
               as("Wishlist " + wishlist + "should have been deleted").
               isFalse();
    }
}