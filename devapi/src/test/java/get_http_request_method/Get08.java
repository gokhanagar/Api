package get_http_request_method;

import base_urls.JsonPlaceHolderBaserUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaserUrl {

     /*
    Given https://jsonplaceholder.typicode/todos/2
    When I send a Get request to the Url
    Then the actual data should be as following:
        {
        "userId":1,
        "id":2,
        "title": "quis ut nam facilis et officia qui",
        "completed":false
        }
     */

    @Test
    public void get08() {
        //Set the Url
        spec.pathParams("bir", "todos", "iki", 2);

        //Set the expected data
        //Map<String, Object>

        Map<String, Object> expectedData = new HashMap<>();

        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        //Send the Get request and Get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}");

        //Validation
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals("beklenen ve karsilasilan veriler farkli", expectedData.get("userId"), actualData.get("userId"));
        assertEquals("beklenen ve karsilasilan veriler farkli", expectedData.get("id"), actualData.get("id"));
        assertEquals("beklenen ve karsilasilan veriler farkli", expectedData.get("title"), actualData.get("title"));
        assertEquals("beklenen ve karsilasilan veriler farkli", expectedData.get("completed"), actualData.get("completed"));


    }

    @Test
    public void testWithPojo() {
        //Set the Url
        spec.pathParams("bir", "todos", "iki", 2);

        //Set the expected data
        Todo expectedTodo = new Todo(1, 2, "quis ut nam facilis et officia qui", false);

        System.out.println(expectedTodo);

        //Send the Get request and Get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}");

        //2. yol Validation icin
        Todo actualTodo = response.as(Todo.class);
        System.out.println("Gelen actual data: " + actualTodo);

        assertEquals("Beklenen data karsilasilan ile uyusmadi", expectedTodo.getUserId(), actualTodo.getUserId());
        assertEquals("Beklenen data karsilasilan ile uyusmadi", expectedTodo.getTitle(), actualTodo.getTitle());
        assertEquals("Beklenen data karsilasilan ile uyusmadi", expectedTodo.getId(), actualTodo.getId());
        assertEquals("Beklenen data karsilasilan ile uyusmadi", expectedTodo.isCompleted(), actualTodo.isCompleted());


    }


}
