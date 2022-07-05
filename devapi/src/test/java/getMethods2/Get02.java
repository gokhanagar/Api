package getMethods2;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Get02 {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/10001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "facebook"
        And
            Server is "Cowboy"
     */

   @Test
   public void get01(){

       //1. Set the url
       String url = "https://restful-booker.herokuapp.com/booking/10001";

       //2. Set the expected data

       //3. Type code to send request
       Response response =given().when().get(url);
        response.prettyPrint();

        //4. Do assertions
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        assertTrue(response.asString().contains("Not Found"));
        assertFalse(response.asString().contains("facebook"));

       assertTrue(response.header("Server").equals("Cowboy"));











   }






}
