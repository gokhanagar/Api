package getMethods;

import base_urls.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get06 extends DummyBaseUrl {

/*
    http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
   1) Butun calisanlarin isimlerini consola yazdıralim
   2) 3. calisan kisinin ismini konsola yazdıralim
   3) Ilk 5 calisanin adini konsola yazdiralim
   4) En son calisanin adini konsola yazdiralim
 */

    @Test
    public void get06() {

        //Set the url
        spec.pathParams("bir", "api", "iki", "v1", "uc", "employees");

        //Set the expected data
        //Send the Get Request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}/{uc}");
        //   response.prettyPrint();

        //validation
        JsonPath json = response.jsonPath();
        System.out.println(json.getList("data.employee_name"));
        System.out.println(json.getString("data[2].employee_name"));
        System.out.println(json.getString("data.employee_name[0,1,2,3,4]"));
        System.out.println(json.getString("data.employee_name[-1]"));

        assertEquals(200, response.getStatusCode());
        assertEquals("Ashton Cox", json.getString("data.employee_name[2]"));
        assertEquals("Doris Wilder", json.getString("data.employee_name[-1]"));


    }


}
