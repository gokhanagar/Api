package get_http_request_method;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

public class Get06 extends DummyBaseUrl {

    /*
    When I send a GET request to the URL https://dummy.restapiexample.com/api/v1/employees
    Then HTTP Status Code should be 200
    And Content Type should be JSon
    And Status Line should be HTTP/1.1 200 Ok
    And User can see following employees in the system
    Doris Wilder, Jenette Caldwell, Bradley Greer
     */

    @Test
    public void get06() {
        //Set the url
        spec.pathParams("bir", "api", "iki", "v1", "uc", "employees");

        //Set the expected data
        //Send the Get Request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}/{uc}");
        response.prettyPrint();

        //validation
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK").
                body("data.employee_name", hasItems("Doris Wilder", "Jenette Caldwell", "Bradley Greer"));

    }


}
