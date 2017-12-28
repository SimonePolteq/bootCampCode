package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

public class ValidateSupplierProductTest extends TestShopScenario {

    @Test
    private void changeSupplier() {

        //Onder suppliers, verander de selectbox naar <AppleStore>
        Select dropdown = new Select(driver.findElement(By.cssSelector("[name='supplier_list']")));
        dropdown.selectByVisibleText("AppleStore");

        //Valideer dat in de lijst met producten de MacBook Air aanwezig is.
        List<WebElement> allOptions = driver.findElements(By.cssSelector("[class='product-name']"));
        for (int i=0; i < allOptions.size();i++){
            System.out.println(allOptions.get(i).getAttribute("title"));

            //TODO string compare instead
            if (allOptions.get(i).getAttribute("title") == "MacBook Air") {
                System.out.println("hoera gevonden");
                boolean foundProduct = true;
            }
            else {
                System.out.println("nog even verder zoeken");
            }
            //TODO assert true
        }
    }
}

