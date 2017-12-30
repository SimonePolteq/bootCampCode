package homework;

import chapterNine.TestShopScenarioAdvanced;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.HeadAllPagesPage;
import pages.HomePage;
import pages.MyWishlistsPage;

public class DeleteWishListTest extends TestShopScenarioAdvanced {

    @Test
    public void deleteWishListTest() {

        HomePage homePage = new HomePage(driver);
        MyWishlistsPage myWishlistsPage = new MyWishlistsPage(driver);
        //todo betere naam verzinnen
        HeadAllPagesPage headAllPagesPage = new HeadAllPagesPage(driver);

        String wishlist = "Testje"; //todo must wishlist <Feel the pain>

        //login
        headAllPagesPage.login("simone@russchen.com", "1qazxsw2");
        //todo check signed in

        //ga naar My wishlists & check dat we er zijn
        homePage.selectMyWishLists();
        Assertions.assertThat(myWishlistsPage.getTextPageHeading())
               .as("Should be on My Wishlists page")
                .isEqualToIgnoringCase("My wishlists");

        //Check if wishlist is available, otherwise create
        if (myWishlistsPage.isPresentWishlist(wishlist)) {
            System.out.println("wishlist is already available, no need to creare");
        }
        else {
            System.out.println("create wishlist:" +wishlist);
            myWishlistsPage.createWishlist(wishlist);
            Assertions.assertThat(myWishlistsPage.isPresentWishlist(wishlist)).as("Wishlist " + wishlist + "should have been added").isTrue();
        }

        //Delete wishlist
        myWishlistsPage.deleteWishlist(wishlist);

        //Check deletion was succesfull
        Assertions.assertThat(myWishlistsPage.isPresentWishlist(wishlist)).as("Wishlist " + wishlist + "should have been deleted").isFalse();
    }//end of test
}

