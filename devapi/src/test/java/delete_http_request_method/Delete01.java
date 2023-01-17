package delete_http_request_method;

import base_urls.JsonPlaceHolderBaserUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete01 extends JsonPlaceHolderBaserUrl {

    /*
    When
        I send DELETE request to the url https://jsonplaceholder.typicode.com/todos/198

     Then
         Status code is 200
         And response body is like {}
     */

    @Test
    public void delete01() {

        //Set the url
        spec.pathParams("bir", "todos", "iki", 198);

        //Set the expected data
        Map<String, Object> expectedData = new HashMap<>();

        //Send the Delete request and get the response
        Response response = given().spec(spec).when().delete("/{bir}/{iki}");

        //validate
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData, actualData);


    }


}