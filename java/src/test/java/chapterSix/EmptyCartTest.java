package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class EmptyCartTest extends TestShopScenario {

    @Test
    public void FillCart() {
        login();
        
        //element alleen displayed indien leeg
        boolean isEmpty = driver.findElement(By.className("ajax_cart_no_product")).isDisplayed();
        if (isEmpty) {
            System.out.println("card is empty so need to add product");
            addProductToCart();

        } else {
            System.out.println("product already in card so no need to add one");
            //do nothing else
        }

        //Click op de cart
        //TODO deze werkt nog niet
        WebElement cart = (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[title='View my shopping cart']")));
        cart.click();

        //Verwijder de item
        WebElement deleteProductIcon = driver.findElement(By.cssSelector("[title='remove this product from my cart']"));

        //Valideer dat de cart nu leeg is
        String TextActual = driver.findElement(By.className("ajax_cart_no_product")).getText();
        String TextExpected = "(empty)";
        String TextError= "Check if cart is empty";
        Assertions.assertThat(TextActual).as(TextError).isEqualTo(TextExpected);
    }

        private void login() {
            //TODO met parameters username en pw
            driver.findElement(By.className("login")).click();
            driver.findElement(By.id("email")).sendKeys("simone.russchen@polteq.com");
            driver.findElement(By.id("passwd")).sendKeys("bootcamp");
            driver.findElement(By.id("SubmitLogin")).click();
        }

        private void addProductToCart() {
            //Click op <ipod> bij het onderdeel TAGS
            WebElement ipodTag;
            ipodTag = (new WebDriverWait(driver, 2)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='More about ipod']")));
            ipodTag.click();

            //Click op de naam van <iPod shuffle> zodat de product pagina wordt geopened
            WebElement iPodShuffle;
            iPodShuffle = (new WebDriverWait(driver, 2)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='iPod shuffle']")));
            //TODO als xpath
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

