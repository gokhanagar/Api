package getMethods;

import base_urls.HerokuappBaseUrl;
import base_urls.JsonPlaceHolderBaserUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get09 extends JsonPlaceHolderBaserUrl {
    /*
        https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
         Dönen response un
         Status kodunun 200, dönen body de,
         "userId": 1,
         "id": 2,
          "title": "quis ut nam facilis et officia qui",
          "completed": false olduğunu test edin…
 */


    @Test
    public void get09() {

        //Set the url
        spec.pathParams("bir", "todos", "iki", 2);

        //Set the expected Data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);


        //Get the request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}");
        response.prettyPrint();

        //Validation
        //1.way

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("userId", equalTo(1), "id", equalTo(2),
                        "title", equalTo("quis ut nam facilis et officia qui"),
                        "completed", equalTo(false));


        //2. way
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

        //3.Way
        JsonPath json = response.jsonPath();
        assertEquals(json.getInt("userId"), 1);
        assertEquals(json.getInt("id"), 2);
        assertEquals(json.getString("title"), "quis ut nam facilis et officia qui");
        assertEquals(json.getBoolean("completed"), false);


    }


}
