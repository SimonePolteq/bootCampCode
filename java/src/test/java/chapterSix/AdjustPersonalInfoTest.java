package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class AdjustPersonalInfoTest extends TestShopScenario {

    @Test
    //OEFENING 6.0.4
    //alternatieve oplossing: lees de naam, check of deze nieuw of oud is
    //String text =driver.findElement(By.cssSelector("[class='header_user_info']")).getText();
    //dit geeft mijn naam terug

    private void adjustInfo() {

        String password="bootcamp";

        //Als er nog geen user is ingelogd, log dan eerst in
        //TODO test op de tekst van header_user_info want dat is er altijd, bij ingelogd zelfs 2x
        //DIT IS EEN HELE LELIJKE OPLOSSING
        //EN GAAT FOUT INDIEN INGELOGD
        //boolean isLoggedIn=driver.findElement(By.className("logout")).isDisplayed();
        boolean isLoggedOut=driver.findElement(By.className("login")).isDisplayed();
        if (isLoggedOut){
            System.out.println("user is logged out");
            login("simone.russchen@polteq.com",password );
        }
        else {
            System.out.println("user is already logged in");
        }

        //Click op my personal information (icon) om naar de correct pagina te gaan
        driver.findElement(By.className("icon-user")).click();
        //Bekijk de huidige voornaam
        String voornaam= driver.findElement(By.id("firstname")).getAttribute("Value");
        System.out.println("huidige voornaam="+voornaam);
        //als dit de zelfde naam is al waarmee het account is aangemaakt: verander de huidige naam in <naam2>.
        // Is de huidige naam <naam2> dan moet de naam veranderd wordt in de naam adres waarmee het account was aangemaakt. Indien een ander naam wordt gevonden dan moet de naam veranderd worden naar de initiële naam tijdens account creatie.
//TODO vragen wat het verschil is , snap opdracht niet
        boolean resultOfComparison =voornaam.equals("Simone");
        if (resultOfComparison) {
            //veld leeg
            driver.findElement(By.id("firstname")).clear();
            //enter name
            driver.findElement(By.id("firstname")).sendKeys("Renate");
        }
        else { driver.findElement(By.id("firstname")).sendKeys("Simone");
        }

        //verplicht eerst ook weer wachtwoord invoeren
        driver.findElement(By.name("old_passwd")).sendKeys(password);
        //Save de verandering
        driver.findElement(By.name("submitIdentity")).click();
        //Valideer dat de verandering succesvol is
        //TODO dit klopt nog niet
        //TODO wait for visibility
        Assertions.assertThat(driver.findElement(By.className("alert alert-success")).isDisplayed()).as("Should display successmessage on changing personal info").isTrue();
    }

    private void login(String email, String password){
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("SubmitLogin")).click();
        //System.out.println("maar nu is user logged in");
    }
}
