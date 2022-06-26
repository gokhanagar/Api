package getMethods;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get02 extends HerokuappBaseUrl {
    /*
    "https://restful-booker.herokuapp.com/booking/5 "
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in “Jim"
    ve totalprice’in 111
    ve checkin date'in 2018-01-01"oldugunu test edin
    */

    @Test
    public void get02() {
        //Set the url
        spec.pathParams("bir", "booking", "iki", 5);

        //Set the expected data
        Map<String, Object> bookingData = new HashMap<String, Object>();
        bookingData.put("checkin", "2018-01-01");
        bookingData.put("checkout", "2019-01-01");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Jim");
        expectedData.put("totalprice", 111);
        expectedData.put("bookingdates", bookingData);

        //Send the get request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}");
        response.prettyPrint();

        //1. Validation
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo("Jim"),
                        "totalprice", equalTo(111),
                        "bookingdates.checkin", equalTo("2018-01-01"));


        //2.Validation
        JsonPath json = response.jsonPath();

        assertEquals(json.getString("firstname"), "Jim");
        assertEquals(json.getInt("totalprice"), 111);
        assertEquals(json.getString("bookingdates.checkin"), "2018-01-01");

        //3.way
        System.out.println(expectedData);
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(bookingData.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingData, actualData.get("bookingdates"));

    }

}
