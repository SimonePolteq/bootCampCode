package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class EmptyCartTest extends TestShopScenario {

    @Test
    //OEFENING 6.0.3
    public void FillCart() {

        login("simone.russchen@polteq.com", "bootcamp");
        Assertions.assertThat(driver.findElement(By.cssSelector("[class='header_user_info']")).getText())
                .as("user should be logged in")
                .isNotEqualToIgnoringCase("Sign in");

        //element alleen displayed indien leeg
        String cartContent = driver.findElement(By.className("ajax_cart_no_product")).getText();
        if (cartContent.equalsIgnoreCase("(empty)")) {
            System.out.println("card is empty so need to add product");
            addProductToCart();
            //check product is added
            Assertions.assertThat(driver.findElement(By.xpath("//span[@class='ajax_cart_quantity unvisible']")).getText())
                    .as("product should have been added")
                    .isEqualTo("1");
        }
        else {
            System.out.println("product already in card so no need to add one");
            //do nothing else
        }

        //Click op de cart
        WebElement cart = (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='View my shopping cart']")));
        cart.click();

        //Verwijder de item
        WebElement deleteProductIcon = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='icon-trash']")));
        deleteProductIcon.click();

        //Valideer dat de cart nu leeg is
        Assertions.assertThat( new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("ajax_cart_no_product"))).getText())
                .as(" Check if cart is empty" )
                .isEqualTo("(empty)");
    }

    private void login(String email, String password) {
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("SubmitLogin")).click();
    }

        private void addProductToCart () {
            //Click op <ipod> bij het onderdeel TAGS
            WebElement ipodTag;
            ipodTag = (new WebDriverWait(driver, 2)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='More about ipod']")));
            ipodTag.click();

            //Click op de naam van <iPod shuffle> zodat de product pagina wordt geopened
            WebElement iPodShuffle;
            iPodShuffle = (new WebDriverWait(driver, 2)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='iPod shuffle']")));
            iPodShuffle.click();

            //Voeg de <iPod Shuffle> toe aan de cart (Tip: na een paar keer vastlopen, kijk hoger in de DOM)
            WebElement addToCartButton;
            addToCartButton = (new WebDriverWait(driver, 2)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id='add_to_cart']")));
            addToCartButton.click();

            //Click op de knop continue shopping
            //driver.switchTo().
            WebElement continueShoppingButton;
            //LET OP!! al wel aanwezig maar nog niet visible does kan je er niet op klikken. Dus andere conditie
            continueShoppingButton = (new WebDriverWait(driver, 4)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[title='Continue shopping']")));
            continueShoppingButton.click();
            System.out.println("Product is toegevoegd");
        }
    }

