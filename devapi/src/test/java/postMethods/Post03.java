package postMethods;

import base_urls.JsonPlaceHolderBaserUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03 extends JsonPlaceHolderBaserUrl {
      /*
    https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
     {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }
Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
   {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false,
     "id": …
    }
     */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("bir", "todos");

        //Set the expected Data
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("id", 201);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);


        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{bir}");
        response.prettyPrint();

        //Validation
        JsonPath json = response.jsonPath();

        assertEquals(expectedData.get("userId"), json.getInt("userId"));
        assertEquals(expectedData.get("id"), json.getInt("id"));
        assertEquals(expectedData.get("title"), json.getString("title"));
        assertEquals(expectedData.get("completed"), json.getBoolean("completed"));
        assertEquals(201, response.statusCode());


    }

}
