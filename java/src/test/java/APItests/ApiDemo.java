package APItests;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class ApiDemo {

    @Test
    public void verstappen2017_returns_200() {
       given().when().get("http://ergast.com/api/f1/2017/drivers/max_verstappen/results.json")
               .then().statusCode(200);
    }

    @Test
    public void bodyContains_maxverstappen2017() {
        given().when().get("http://ergast.com/api/f1/2017/drivers/max_verstappen/results.json")
                .then().body("MRData.RaceTable.driverId", equalTo("max_verstappen"));
               // .then().body(contains("max_verstappen"));  faalt want verwacht iets iterable
    }

    @Test
    public void bodyContains_maxverstappen2017Alternatief() {
        given().when().get("http://ergast.com/api/f1/2017/drivers/max_verstappen/results.json")
                .then()
                .assertThat().body("MRData.RaceTable.driverId", containsString("max_verstappen"));
    }

    @Test
    public void printBodyToConsole() {
    String response = given().when().get("http://ergast.com/api/f1/2017/drivers/max_verstappen/results.json").getBody().prettyPrint();
        System.out.println(response);
    }
}

