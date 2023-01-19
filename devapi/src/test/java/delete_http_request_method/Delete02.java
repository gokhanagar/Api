package delete_http_request_method;

import base_urls.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyBaseUrl {
     /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

 Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
 {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
 }
     */

    @Test
    public void delete02() {

        //Set the url
        spec.pathParams("bir", "api", "iki", "v1", "uc", "delete", "dort", "2");

        //Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("data", 2);
        expectedData.put("status", "success");
        expectedData.put("message", "Successfully! Record has been deleted");

        //Send the delete request and get the response
        Response response = given().spec(spec).when().delete("/{bir}/{iki}/{uc}/{dort}");

        //Validation
        response.then().assertThat().statusCode(200);

        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("data"), actualData.get("data"));
        assertEquals(expectedData.get("status"), actualData.get("status"));
        assertEquals(expectedData.get("message"), actualData.get("message"));


    }


}