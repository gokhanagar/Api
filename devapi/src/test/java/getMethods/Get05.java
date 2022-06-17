package getMethods;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get05 extends HerokuappBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking/5 url’ine bir request yolladigimda
    HTTP Status Code’unun 200
    ve response content type’inin “application/JSON” oldugunu
    ve response body’sinin asagidaki gibi oldugunu test edin
    {
    "firstname": "Jim",
    "lastname": "Ericsson",
    "totalprice": 299,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2017-07-09",
        "checkout": "2022-04-29"
    },

    */


    @Test
    public void get05() {

        //Set the url
        spec.pathParams("bir", "booking", "iki", 5);

        //Set the expected Data
        Map<String, Object> bookingData = new HashMap<>();
        bookingData.put("checkin", "2017-07-09");
        bookingData.put("checkout", "2022-04-29");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Jim");
        expectedData.put("lastname", "Ericsson");
        expectedData.put("totalprice", 299);
        expectedData.put("depositpaid", false);
        expectedData.put("bookingdates", bookingData);


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}");
        response.prettyPrint();

        //Validation
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(bookingData.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));


    }


}
