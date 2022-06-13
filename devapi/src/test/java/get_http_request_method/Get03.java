package get_http_request_method;

import base_urls.JsonPlaceHolderBaserUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 extends JsonPlaceHolderBaserUrl {
    /*
    Given https://jsonplaceholder.typicode.com/todos/23
    When user sends a GET request to the url
     Then HTTP status code should be 200
     And Response format should be "application/json"
     And "title" is "et itaque necessitatibus maxime molestiae qui quas velit"
     And "completed" is false
     And "userId" is 2
     */

    @Test
    public void get03() {

        //Set the url
        spec.pathParams("bir", "todos", "iki", 23);

        //Set the expected data

        //Send the Get Request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}");

        //validation
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).body("userId", equalTo(2));


    }


}
