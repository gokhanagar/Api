package postMethods;

import base_urls.JsonPlaceHolderBaserUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04 extends JsonPlaceHolderBaserUrl {
     /*
    https://jsonplaceholder.typicode.com/todos url ‘ine bir request gönderildiğinde
 Request body{
 "userId": 21,
 "id": 201,
 "title": "Tidy your room",
 "completed": false
}
 Status kodun 201,response body ‘nin ise

{
 "userId": 21,
 "id": 201,
 "title": "Tidy your room",
 "completed": false
 }
     */

    @Test
    public void post04() {
        //Set the Url
        spec.pathParam("bir", "todos");

        //Set the expected data
        Todo expectedTodo = new Todo(21, 201, "Tidy your room", false);

        //Send the post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).
                body(expectedTodo).when().post("/{bir}");
        response.prettyPrint();

        //Validation
        Todo actualData = response.as(Todo.class);
        assertEquals(201, response.getStatusCode());
        assertEquals(expectedTodo.getUserId(), actualData.getUserId());
        assertEquals(expectedTodo.getId(), actualData.getId());
        assertEquals(expectedTodo.getTitle(), actualData.getTitle());
        assertEquals(expectedTodo.isCompleted(), actualData.isCompleted());


    }

}
