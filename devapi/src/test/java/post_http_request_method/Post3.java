package post_http_request_method;

import base_urls.JsonPlaceHolderBaserUrl;
import data.JsonPlaceHolderData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static data.JsonPlaceHolderData.expectedDatasetup;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Post3 extends JsonPlaceHolderBaserUrl {
    /*
    When
        I send Post request to the url https://jsonplaceholder.typicode.com/todos
        with the request body {
                                "userId": 55,
                                "id": 201,
                                "title": "Tidy your room",
                                "completed": false
                            }
     Then
         Status code is 201
         And response body is like {
                                        "userId": 55,
                                        "id": 201,
                                        "title": "Tidy your room",
                                        "completed": false
                                    }
     */

    @Test
    public void post03() {
        //Set the base Url
        spec.pathParams("bir", "todos");

        //Set the expected data
        Map<String, Object> expectedData = expectedDatasetup();

        //Send the Post request and Get the response
        Response response = given().spec(spec).auth().basic("admin", "1234").contentType(ContentType.JSON).
                body(expectedData).when().post("/{bir}");

        response.prettyPrint();

        //validate
        response.then().assertThat().statusCode(201).
                body("userId", equalTo(55), "title", equalTo("Tidy your room"),
                        "completed", equalTo(false));


    }


}
