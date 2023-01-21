package get_http_request_method;


import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 extends HerokuappBaseUrl {

    /*
    Given https://restful--booker.herokuapp.com/booking/10000
    When user sends a GET request to the url
     Then HTTP status code should be 404
     And response body contains "Not Found"
     And Status Line should be HTTP/1.1 404 Not Found
     And body does not contain " twitter"
     and Server is "Cowboy
     */

    @Test
    public void get02() {

        // RequestSpecification spec;
        //spec= new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
        //Set the url
        spec.pathParams("bir", "booking", "iki", 10000);

        //Set the expected data

        // Send the get request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}");

        //validation
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        System.out.println(response.asString());

        assertEquals("Not Found", response.asString());
        assertTrue(response.asString().contains("Not Found"));
        assertFalse(response.asString().contains("twitter"));

        System.out.println(response.getHeader("Server"));
        assertEquals(response.getHeader("Server"), "Cowboy");


    }


}