package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.List;

    public class ValidateSupplierProductTest extends TestShopScenario {

    @Test
    //OEFENING 6.0.5
    private void changeSupplier() {

        //initialize checking boolean
        boolean foundProduct=false;

        //Onder suppliers, verander de selectbox naar <AppleStore>
        Select dropdown = new Select(driver.findElement(By.cssSelector("[name='supplier_list']")));
        dropdown.selectByVisibleText("AppleStore");

        //itereer door lijst met producten en check of de MacBook Air aanwezig is.
        //opm: beter de lijst ervoor zetten
        List<WebElement> allOptions = driver.findElements(By.cssSelector("[class='product-name']"));
        for (int i=0; i < allOptions.size();i++){
            System.out.println(allOptions.get(i).getAttribute("title"));

            boolean resultOfComparison =allOptions.get(i).getAttribute("title").equals("MacBook Air");
            if (resultOfComparison) {
                foundProduct = true;
            }
        }
        //Validate that Macbook air is in the list
        Assertions.assertThat(foundProduct).as("Product MacBook Air is not found").isTrue();
        //of : list contains , zodat je kan uitbreiden
    }
}

