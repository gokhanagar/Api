package getMethods2;

import base_urls.JsonPlaceHolderBaserUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get04 extends JsonPlaceHolderBaserUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
	 	    I send a GET request to the Url
	    And
	        Accept type is “application/json”
	    Then
	        HTTP Status Code should be 200
	    And
	        Response format should be "application/json"
	    And
	        There should be 200 todos
	    And
	        "quis eius est sint explicabo" should be one of the todos title
	    And
	        2, 7, and 9 should be among the userIds
     */

    @Test
    public void get04(){
        //1. Set the url
        spec.pathParam("first","todos");

        //2. Set the expected data

        //3. Type code to send request
        Response response = given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();

        //4. Do assertions

    }



}
