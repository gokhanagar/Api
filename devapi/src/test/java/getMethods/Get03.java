package getMethods;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class Get03 extends DummyBaseUrl {
     /*
    http://dummy.restapiexample.com/api/v1/employees url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve employees sayisinin 24
    ve employee'lerden birinin "Garrett Winters"
    ve gelen yaslar icinde 43, 61, ve 23 degerlerinden birinin oldugunu test edin
     */

    @Test
    public void get03() {

        //Set the url
        spec.pathParams("bir", "api", "iki", "v1", "uc", "employees");

        //Set the expected Data
        //Get request and get response
        Response response = given().spec(spec).when().get("/{bir}/{iki}/{uc}");
        response.prettyPrint();

        //Validation
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("data.id", hasSize(24),
                        "data.employee_name", hasItem("Garrett Winters"),
                        "data.employee_age", hasItems(23, 43, 61));


    }


}
