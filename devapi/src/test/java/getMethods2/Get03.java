package getMethods2;

import base_urls.JsonPlaceHolderBaserUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 extends JsonPlaceHolderBaserUrl {
    /*
      Given
          https://jsonplaceholder.typicode.com/todos/23
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
      And
         Response format should be "application/json"
     And
          "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
     And
          "completed" is false
     And
         "userId" is 2
   */

    @Test
    public void get01() {
        //Set the url
        spec.pathParams("first", "todos", "second", 23);


        //2. Set the expected data

        //3. Type code to send request
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //4. Do assertion
        response.then().assertThat().statusCode(200).contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false), "userId", equalTo(2));


    }


}
