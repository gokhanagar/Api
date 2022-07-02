package getMethods;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get01 extends HerokuappBaseUrl {

    /*
         https://restful-booker.herokuapp.com/booking/7 url'ine
        accept type'i "application/json" olan GET request'i yolladigimda
        gelen response'un
        status kodunun 200
        ve content type'inin "application/json"


        ve firstname'in "Mary"
        ve lastname'in "Jones"
        ve checkin date'in 2018-10-07"
        ve checkout date'in 2020-09-30 oldugunu test edin
     */

    @Test
    public void get01() {

        //Set the url
        spec.pathParams("bir", "booking", "iki", 7);

        //Set the expected data

        //Send the get request and get the response
        Response response = given().spec(spec).when().get("/{bir}/{iki}");
        response.prettyPrint();

        //1. Validation
        //response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
        //        body("firstname",equalTo("Susan")).
        //        body("lastname",equalTo("Smith")).
        //        body("totalprice",equalTo(147)).
        //        body("depositpaid",equalTo(true)).
        //        body("bookingdates.checkin",equalTo("2021-08-10")).
        //        body("bookingdates.checkout",equalTo("2022-01-15"));

        //2. Validation
        JsonPath json = response.jsonPath();
        System.out.println(json.getString("firstname"));

        assertEquals(json.getString("firstname"), "Susan");
        assertEquals(json.getString("lastname"), "Smith");
        assertEquals(json.getString("totalprice"), "147");
        assertTrue(json.getBoolean("depositpaid"));
        assertEquals(json.getString("bookingdates.checkin"), "2021-08-10");
        assertEquals(json.getString("bookingdates.checkout"), "2022-01-15");


    }


}
