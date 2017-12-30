package homework;

import chapterNine.TestShopScenarioAdvanced;
import org.assertj.core.api.Assertions;
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
        System.out.println("check of wishlist er al is");
        if (myWishlistsPage.isPresentWishlist(wishlist)) {
            System.out.println("wishlist " + wishlist + " is already available, so no need to create");
        }
        else {
            System.out.println("wishlist " + wishlist + " not already there, so need to cerate it"); //todo weghalen
            myWishlistsPage.createNewWishlist(wishlist);
            Assertions.assertThat(myWishlistsPage.isPresentWishlist(wishlist)).as("Wishlist " + wishlist + "should have been added").isTrue();
        }

        //Delete wishlist
        myWishlistsPage.deleteWishlist(wishlist);

        //Check deletion was succesfull
       //TODO comment weghalen Assertions.assertThat(myWishlistsPage.isPresentWishlist(wishlist)).as("Wishlist " + wishlist + "should have been deleted").isFalse();
    }
}

