package getMethods;

import base_urls.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get07 extends DummyBaseUrl {
      /*
         http://dummy.restapiexample.com/api/v1/employees
        url ine bir istek gönderildiğinde,
        status kodun 200,
        gelen body de,
        5. çalışanın isminin "Airi Satou" olduğunu ,
        6. çalışanın maaşının "372000" olduğunu ,
        Toplam 24 tane çalışan olduğunu,
        "Rhona Davidson" ın employee lerden biri olduğunu
        "21", "23", "61" yaşlarında employeeler olduğunu test edin
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
        JsonPath json = response.jsonPath();

        System.out.println(json.getString("data.employee_name[4]"));
        System.out.println(json.getString("data.employee_salary[5]"));
        System.out.println(json.getList("data.id").size());

        assertEquals("Airi Satou", json.getString("data.employee_name[4]"));
        assertEquals(372000, json.getInt("data.employee_salary[5]"));
        assertEquals(24, json.getList("data.id").size());


    }
}