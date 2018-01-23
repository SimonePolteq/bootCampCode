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
        Select dropdown = new Select(driver.findElement(By.cssSelector("[name='supplier_list']"))); //betere naam: supplierList
        dropdown.selectByVisibleText("AppleStore");

        //itereer door lijst met producten en check of de MacBook Air aanwezig is.
        //alternative: iterate through list and add the products to a new list. Assert that product is in that list
        List<WebElement> allOptions = driver.findElements(By.cssSelector("[class='product-name']"));
        //by xpath: //ul[@id='product_list']//a[@class='product-name']
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

/* alternatief:

@Test
    public void validateSupplierProductTest() {

        // Select AppleStore from supplier list drop-down
        Select supplierList = new Select(driver.findElement(By.name("supplier_list")));
        supplierList.selectByVisibleText("AppleStore");

        // Make list of all products found
        List<WebElement> allAppleProducts =
                driver.findElements(By.xpath("//ul[@id='product_list']//*[@class='product-name']"));

        // Create empty string list for the product names
        List<String> productNames = new ArrayList<String>();

        // Add the name of each found Apple product to the productNames list
        for (int i = 0; i < allAppleProducts.size(); i++) {
            String title = allAppleProducts.get(i).getAttribute("title");
            productNames.add(title);
        }

        System.out.println(productNames);

        // Assert that 'MacBook Air' is in the productNames list
        Assertions.assertThat(productNames).as("List should contain 'MacBook Air'").contains("MacBook Air");
    }
 */
