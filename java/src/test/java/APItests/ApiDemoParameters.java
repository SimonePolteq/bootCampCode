package APItests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ApiDemoParameters {

    private int season = 2017;
    private String series = "f1";
    private String driver ="max_verstappen";
    private String baseUrl = "http://ergast.com/api";

    @Test
    //we geven een parameter voor de url mee om het variabel te maken
    //in de url hebben we dus 2017 vervangen voor {season}
    public void pathParam() {
        given().pathParam("season",2017)
                .when().get("http://ergast.com/api/f1/{season}/drivers/max_verstappen/results.json")
                .then().assertThat().body("MRData.RaceTable.driverId", containsString("max_verstappen"));
    }

    @Test
    public void pathMultipleParam() {
        given().pathParam("season",season).pathParam("series",series).pathParam("driver", driver)
                .when().get("http://ergast.com/api/{series}/{season}/drivers/{driver}/results.json")
                .then().assertThat().body("MRData.RaceTable.driverId", containsString("max_verstappen"));
    }

    //baseUrl is een methode
    @Test
    public void withBaseUrl() {
        given().baseUri(baseUrl)
                .pathParam("season",season).pathParam("series",series).pathParam("driver", driver)
                .when().get("/{series}/{season}/drivers/{driver}/results.json")
                .then().assertThat().body("MRData.RaceTable.driverId", containsString("max_verstappen"));
    }
}

