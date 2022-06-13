package get_http_request_method;

import base_urls.JsonPlaceHolderBaserUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class Get04 extends JsonPlaceHolderBaserUrl {
     /*
    Given https://jsonplaceholder.typicode.com/todos
    When I send a GET request to the url
    And Accept type is "application/json
     Then HTTP status code should be 200
     And Response format should be "application/json"
     And There should be 200 todos
     And "  uis eius est sint explicabo" should be one of the todos
     And 2,7, and 9 should be among the userIds
     */


    @Test
    public void get04() {

        //Set the url
        spec.pathParam("bir", "todos");

        //Set the expected data

        //Send the Get Request and get the response
        Response response = given().spec(spec).when().get("/{bir}");

        //validation
        response.then().assertThat().contentType(ContentType.JSON).statusCode(200).
                body("id", hasSize(200)).
                body("title", hasItem("quis eius est sint explicabo")).
                body("userId", hasItems(2, 7, 9));


    }


}
