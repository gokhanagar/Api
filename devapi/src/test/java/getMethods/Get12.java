package getMethods;

import base_urls.JsonPlaceHolderBaserUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12 extends JsonPlaceHolderBaserUrl {
    /*
    GetRequestWithObjectMapper01:
 https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
Dönen response ‘un status kodunun 200 ve body kısmının
 {
 "userId": 10,
 "id": 198,
 "title": "quis eius est sint explicabo",
 "completed": true
 }
     */

    @Test
    public void get12() {

        //Set the url
        spec.pathParams("bir", "todos", "iki", 198);

        //Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 10);
        expectedData.put("id", 198);
        expectedData.put("title", "quis eius est sint explicabo");
        expectedData.put("completed", true);

        //Get the request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}");
        response.prettyPrint();

        //Validation
        JsonPath json = response.jsonPath();

        assertEquals(expectedData.get("userId"), json.getInt("userId"));
        assertEquals(expectedData.get("id"), json.getInt("id"));
        assertEquals(expectedData.get("title"), json.getString("title"));
        assertEquals(expectedData.get("completed"), json.getBoolean("completed"));


    }


}
