package getMethods;

import base_urls.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyPojo;

import static io.restassured.RestAssured.given;

public class Get13 extends DummyBaseUrl {
    /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                        Status code is 200
                             {
                              "status": "success",
                              "data": {
                                  "id": 1,
                                  "employee_name": "Tiger Nixon",
                                  "employee_salary": 320800,
                                  "employee_age": 61,
                                  "profile_image": ""
                              },
                              "message": "Successfully! Record has been fetched."
                             }

*/
    @Test
    public void test() {

        //Set the url
        spec.pathParams("bir", "api", "iki", "v1", "uc", "employee", "dort", 1);

        //Set the expected data
        DummyPojo expectedData = new DummyPojo(1, "Tiger Nixon", 320800, 61, "");

        //Get the request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}/{uc}/{dort}");
        response.prettyPrint();

        //Validation
        DummyPojo actualData = response.as(DummyPojo.class);
        System.out.println("Gelen gercek bilgi: " + actualData);
        System.out.println(expectedData);


    }


}
