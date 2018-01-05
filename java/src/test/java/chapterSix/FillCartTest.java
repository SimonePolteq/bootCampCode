package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

//OEFENING 6.0.2
public class FillCartTest extends TestShopScenario{

    private void login() {
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).sendKeys("simone.russchen@polteq.com");
        driver.findElement(By.id("passwd")).sendKeys("bootcamp");
        driver.findElement(By.id("SubmitLogin")).click();
    }

    @Test
    public void FillCart() {
        login();
        Assertions.assertThat(driver.findElement(By.cssSelector("[class='header_user_info']")).getText())
                .as("user should be logged in")
                .isNotEqualToIgnoringCase("Sign in");

        //Valider dat cart empty is
        System.out.println("aantal producten in cart voor toevoegen=" + driver.findElement(By.className("ajax_cart_no_product")).getText() );
        String TextActual = driver.findElement(By.className("ajax_cart_no_product")).getText();
        String TextExpected = "(empty)";
        String TextError= "Check if cart is empty";
        Assertions.assertThat(TextActual).as(TextError).isEqualTo(TextExpected);

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

        //Valideer dat er nu 1 product zich in de cart bevindt

        System.out.println("aantal producten in cart na toevoegen =" + driver.findElement(By.cssSelector("[class='ajax_cart_quantity unvisible']")).getText() );
        Assertions.assertThat(driver.findElement(By.cssSelector("[class='ajax_cart_quantity unvisible']")).getText()).
                as("Check if cart contains 1 product").
                isEqualTo("1");
    }
}

