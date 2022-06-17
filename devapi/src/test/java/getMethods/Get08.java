package getMethods;

import base_urls.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get08 extends DummyBaseUrl {

    /*
        http://dummy.restapiexample.com/api/v1/employees
       url ine bir istek gönderildiğinde
       Dönen response un
        Status kodunun 200,
        1)10’dan büyük tüm id’leri ekrana yazdırın ve
       10’dan büyük 14 id olduğunu,
        2)30’dan küçük tüm yaşları ekrana yazdırın ve
         bu yaşların içerisinde en büyük yaşın 23 olduğunu
        3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
         bunların içerisinde “Charde Marshall” olduğunu test edin

    */

    @Test
    public void get08() {
        //Set the url
        spec.pathParams("bir", "api", "iki", "v1", "uc", "employees");

        //Set the expected data
        //Get the request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}/{uc}");
        response.prettyPrint();

        //Validation
        JsonPath json = response.jsonPath();

        assertEquals(200, response.getStatusCode());
        //1)10’dan büyük tüm id’leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
        List<Integer> idList = json.getList("data.findAll{it.id>10}.id");
        System.out.println(idList);
        assertEquals(14, idList.size());

        //2)30’dan küçük tüm yaşları ekrana yazdırın ve
        //  bu yaşların içerisinde en büyük yaşın 23 olduğunu

        List<Integer> yasList = json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(yasList);

        Collections.sort(yasList);
        assertEquals((Integer) 23, yasList.get(yasList.size() - 1));

        //3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
        //  bunların içerisinde “Charde Marshall” olduğunu test edin

        List<String> isimList = json.getList("data.findAll{it.employee_salar< 350000 }.employee_name");
        System.out.println(isimList);

        assertTrue(isimList.contains("Charde Marshall"));


    }
}