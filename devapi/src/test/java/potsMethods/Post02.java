package potsMethods;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerokuappBaseUrl {
      /*
    https://restful-booker.herokuapp.com/booking
    { "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 11111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
 }
     gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
    "booking": {
         "firstname": " Selim ",
         "lastname": " Ak ",
         "totalprice":  11111,
         "depositpaid": true,
         "bookingdates": {
             "checkin": "2020-09-01",
              "checkout": " 2020-09-21”
         },
        }
olduğunu test edin
     */

    @Test
    public void post02() {

        //Set the url
        spec.pathParam("bir", "booking");

        //Set the expected Data
        HashMap<String, Object> bookingData = new HashMap<>();
        bookingData.put("checking", "2020-09-09");
        bookingData.put("checkout", "2020-09-21");

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Selim");
        expectedData.put("lastname", "Ak");
        expectedData.put("totalprice", 11111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingData);


        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{bir}");
        response.prettyPrint();

        //Validation
        JsonPath json = response.jsonPath();

        assertEquals(expectedData.get("firstname"), json.getString("firstname"));
        assertEquals(expectedData.get("lastname"), json.getString("lastname"));
        assertEquals(expectedData.get("totalprice"), json.getInt("totalprice"));
        assertEquals(expectedData.get("depositpaid"), json.getBoolean("depositpaid"));
        assertEquals(bookingData.get("checkin"), json.getString("bookingdates.checkin"));
        assertEquals(bookingData.get("checkout"), json.getString("bookingdates.checkout"));


    }


}
