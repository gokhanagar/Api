package get_http_request_method;


import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 extends HerokuappBaseUrl {

    /*
    Gherkin key words

    Given  => baslangic islemini temsil eder. pre-requisite
    When   => islemin action kismini tanimlar
    And    => tekrar eden islemleri gosterir
    Then   => islemin sonucunu ve dogrulanmasini gosterir
     */

    /*
    Given
            https://restful--booker.herokuapp.com/booking/3
    When
            user sends a request to the url
    Then
            HTTP status code should be 200
    Then
            HTTP status code should be 200
    And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get01() {

        String endpoint = "https://restful-booker.herokuapp.com/booking/3";
        Response response = given().when().get(endpoint);
        response.prettyPrint();

    }

    @Test
    public void test() {

        //RequestSpecification spec;
        //spec= new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();


        //Set the url
        spec.pathParams("bir", "booking", "iki", 3);

        //   /{bir}/{iki}
        //Get request and response
        Response response = given().spec(spec).when().get("/{bir}/{iki}");
        response.prettyPrint();

        //Validation
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");


    }


}