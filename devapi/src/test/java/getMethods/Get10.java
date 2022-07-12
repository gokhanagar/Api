package getMethods;

import base_urls.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
      5. Çalışan isminin "Airi Satou" olduğunu ,
      çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
    {
            “id”:”11”
             "employee_name": "Jena Gaines",
            "employee_salary": "90560",
            "employee_age": "30",
            "profile_image": "" }
        } gibi olduğunu test edin.
*/

    @Test
    public void get10() {

        //Set the Url
        spec.pathParams("bir", "api", "iki", "v1", "uc", "employees");

        //Set the expected data

        //Set the request and Set the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}/{uc}");
        //response.prettyPrint();

        //Validation
        JsonPath json = response.jsonPath();
        Set<Integer> expected = new HashSet<>();
        expected.add(40);
        expected.add(21);
        expected.add(19);


        System.out.println((json.getString("data.employee_name")));
        System.out.println(json.getList("data.employee_name").size());
        System.out.println(json.getInt("data.employee_salary[-2]"));
        System.out.println(json.getList("data.employee_age").containsAll(expected));

        assertEquals("Airi Satou", json.getString("data.employee_name[4]"));
        assertEquals(24, json.getList("data.employee_name").size());
        assertEquals(106450, json.getInt("data.employee_salary[-2]"));


        assertEquals(11, json.getInt("data.id[10]"));
        assertEquals("Jena Gaines", json.getString("data.employee_name[10]"));
        assertEquals(90560, json.getInt("data.employee_salary[10]"));
        assertEquals(30, json.getInt("data.employee_age[10]"));
        assertEquals("", json.getString("data.profile_image[10]"));


    }

}
