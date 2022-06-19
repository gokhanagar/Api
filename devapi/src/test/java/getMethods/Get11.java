package getMethods;

import base_urls.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get11 extends DummyBaseUrl {

       /*
         http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
        Status kodun 200 olduğunu,
        En yüksek maaşın 725000 olduğunu,
        En küçük yaşın 19 olduğunu,
        İkinci en yüksek maaşın 675000
        olduğunu test edin.
     */

    @Test
    public void get11() {

        //Set the URl
        spec.pathParams("bir", "api", "iki", "v1", "uc", "employees");

        //Set the expected data
        //Set the request and set the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}/{uc}");
        //response.prettyPrint();

        //Validation
        JsonPath json = response.jsonPath();

        //Status kodun 200 olduğunu,
        assertEquals(200, response.getStatusCode());

        //En yüksek maaşın 725000 olduğunu,

        List<Integer> salaryList = json.getList("data.employee_salary");
        Collections.sort(salaryList);
        assertEquals((Integer) 725000, salaryList.get(salaryList.size() - 1));

        //İkinci en yüksek maaşın 675000
        assertEquals((Integer) 675000, salaryList.get(salaryList.size() - 2));

        //En küçük yaşın 19 olduğunu,

        List<Integer> ageList = json.getList("data.employee_age");
        Collections.sort(ageList);
        assertEquals((Integer) 19, ageList.get(0));


    }


}
