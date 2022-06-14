package get_http_request_method;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Get07 extends DummyBaseUrl {

     /*
    Given https://dummy.restapiexample.com/api/v1/employee/7
    When I send a Get request to the Url
    Then HTTP Status Code should be 200
    And Response format should be JSon
    And Herrod Chandler should be the employee name
    And "employee_salary":137500 should be the expected salary
    And "id":7 should be expected id
     */

    @Test
    public void get07() {

        //Set the Url
        spec.pathParams("bir", "api", "iki", "v1", "uc", "employee", "dort", 7);

        //Set the expected data
        //Send the Get Request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}/{uc}/{dort}");
        response.prettyPrint();

        //1. Validation
        //response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
        //        body("data.employee_name",equalTo("Herrod Chandler")).
        //        body("data.employee_salary",equalTo(137500)).
        //        body("data.id",equalTo(7));

        //2. Validation
        JsonPath json = response.jsonPath();
        System.out.println(json.getString("data.employee_name"));
        System.out.println(json.getString("data"));
        System.out.println(json.getString("data.employee_salary"));

        int salary = json.getInt("data.employee_salary");
        System.out.println(salary);

        //Hard assertion => Scenario calisir ve ilk hatada calisma durur
        //Soft Assertion tum senaryolari calistirir ve en son da durur hatalari orada cikartir
        assertEquals(json.getString("data.employee_name"), "Herrod Chandler");
        assertEquals(json.getInt("data.employee_salary"), (137500));
        assertEquals(json.getInt("data.id"), 7);


        //Soft Assertion
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(json.getInt("data.employee_salary"), (137500));
        softAssert.assertEquals(json.getString("data.employee_name"), "Herrod Chandler");
        softAssert.assertEquals(json.getInt("data.id"), 7);

        softAssert.assertAll();


    }


}
