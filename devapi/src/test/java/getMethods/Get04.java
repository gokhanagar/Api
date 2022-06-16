package getMethods;

import base_urls.JsonPlaceHolderBaserUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get04 extends JsonPlaceHolderBaserUrl {
    /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response’un
    status kodunun 200
    ve content type'inin "application/json"
    ve response body'deki "userId"'nin 7
    ve "title" in "esse et quis iste est earum aut impedit"
    ve "completed" bolumunun false oldugunu test edin
    */

    @Test
    public void get03() {

        //Set the url
        spec.pathParams("bir", "todos", "iki", 123);

        //Set the expected Data
        //Get request and get response
        Response response = given().spec(spec).when().get("/{bir}/{iki}");
        response.prettyPrint();

        //validation
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("userId", equalTo(7),
                        "title", equalTo("esse et quis iste est earum aut impedit"),
                        "completed", equalTo(false));


    }
}
