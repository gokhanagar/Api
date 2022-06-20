package potsMethods;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends DummyBaseUrl {
        /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
 "name":"Ahmet Aksoy",
 "salary":"1000",
 "age":"18",
  }
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin ,
{
 "status": "success",
 "data": {
 “id”:…
 },
 "message": "Successfully! Record has been added."
 }

olduğunu test edin
     */


    @Test
    public void post01() {

        //Set the Url
        spec.pathParams("bir", "api", "iki", "v1", "uc", "create");

        //Set the expected data
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("name", "Ahmet Aksoy");
        expectedData.put("salary", 1000);
        expectedData.put("age", 18);


        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{bir}/{iki}/{uc}");
        response.prettyPrint();

        //Validation
        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(expectedData.get("name"), ((Map) actualData.get("data")).get("name"));
        assertEquals(expectedData.get("salary"), ((Map) actualData.get("data")).get("salary"));
        assertEquals(expectedData.get("age"), ((Map) actualData.get("data")).get("age"));


    }


}
