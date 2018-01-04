package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AdjustPersonalInfoTest extends TestShopScenario {

    @Test
    //OEFENING 6.0.4

    private void adjustInfo() {

        String password="bootcamp";
        String email="simone.russchen@polteq.com";

        boolean isLoggedIn= ("Sign in".equalsIgnoreCase(driver.findElement(By.cssSelector("[class='header_user_info']")).getText() ));

        if (!isLoggedIn){
            System.out.println("user is already logged in");
        }
        else {
            System.out.println("user is logged out");
            login(email,password );
        }

        //Click op my personal information (icon) om naar de correct pagina te gaan
        driver.findElement(By.className("icon-user")).click();

        //Bekijk de huidige voornaam
        String currentName= driver.findElement(By.id("firstname")).getAttribute("Value");
        System.out.println("current name= "+currentName);
        //als dit de zelfde naam is al waarmee het account is aangemaakt: verander de huidige naam in <naam2>.
        //alternatieve oplossing: lees de naam, check of deze nieuw of oud is
        boolean resultOfComparison =currentName.equals("Simone");
        if (resultOfComparison) {
            //veld leeg
            driver.findElement(By.id("firstname")).clear();
            //enter name
            driver.findElement(By.id("firstname")).sendKeys("Renate");
        }
        else {
            driver.findElement(By.id("firstname")).clear();
            driver.findElement(By.id("firstname")).sendKeys("Simone");
        }

        //verplicht eerst ook weer wachtwoord invoeren
        driver.findElement(By.name("old_passwd")).sendKeys(password);
        //Save de verandering
        driver.findElement(By.name("submitIdentity")).click();

        //Valideer dat de verandering succesvol is
        WebElement alertMessageElement = new WebDriverWait(driver, 10).
                        until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='alert alert-success']")));

        Assertions.assertThat(alertMessageElement.isDisplayed()).as("Should display successmessage on changing personal info").isTrue();
    }

    private void login(String email, String password){
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("SubmitLogin")).click();
        //System.out.println("maar nu is user logged in");
    }
}
