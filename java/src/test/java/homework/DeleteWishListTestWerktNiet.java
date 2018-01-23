package homework;

import chapterNine.TestShopScenarioAdvanced;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.MyWishlistsPageWerktNiet;
import pages.PageHeaderPage;

public class DeleteWishListTestWerktNiet extends TestShopScenarioAdvanced {

    @Test
    public void deleteWishListTest() {

        HomePage homePage = new HomePage(driver);
        MyWishlistsPageWerktNiet myWishlistsPageWerktNiet = new MyWishlistsPageWerktNiet(driver);
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

        Assertions.assertThat(myWishlistsPageWerktNiet.getTextPageHeading())
                .as("Should be on My Wishlists page")
                .isEqualToIgnoringCase("My wishlists");

        //Check if wishlist is available, otherwise create
        System.out.println("check of wishlist er al is");
        if (myWishlistsPageWerktNiet.isPresentWishlist(wishlist)) {
            System.out.println("wishlist " + wishlist + " is already available, so no need to create");
        }
        else {
            System.out.println("wishlist " + wishlist + " unavailable, so create it");
            myWishlistsPageWerktNiet.createNewWishlist(wishlist);
            Assertions.assertThat(myWishlistsPageWerktNiet.isPresentWishlist(wishlist)).as("Wishlist " + wishlist + "should have been added").isTrue();
        }

        //Delete wishlist
        myWishlistsPageWerktNiet.deleteWishlist(wishlist);

        //Check deletion was succesfull
        System.out.println("Check if deletion was succesfull");
        Assertions.assertThat(myWishlistsPageWerktNiet.isPresentWishlist(wishlist)).
                as("Wishlist " + wishlist + " should have been deleted").
                isFalse();

        //Add the wishlist again so the code is 100% re-runable without any code or function adjustments
        System.out.println("add the wishlist again at end of test");
        myWishlistsPageWerktNiet.createNewWishlist(wishlist);
        Assertions.assertThat(myWishlistsPageWerktNiet.isPresentWishlist(wishlist)).as("Wishlist " + wishlist + "should have been added at end of test").isTrue();
    }
}