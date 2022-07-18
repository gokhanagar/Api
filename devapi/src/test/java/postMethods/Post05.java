package postMethods;


import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post05 extends HerokuappBaseUrl {
        /*
    https://restful-booker.herokuapp.com/booking
    request body {
                   "firstname": "Selim",
                   "lastname": "Ak",
                   "totalprice": 15000,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2020-09-09",
                       "checkout": "2020-09-21"
                    }
                 }
   Status code is 200
    response body  {
                            "bookingid": 11,
                            "booking": {
                                "firstname": "Selim",
                                "lastname": "Ak",
                                "totalprice": 15000,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2020-09-09",
                                    "checkout": "2020-09-21"
                                }
                            }
                         }
     */

    @Test
    public void post05() {

        //Set the url
        spec.pathParam("bir", "booking");

        //Set the expected data
        BookingDatesPojo bookingdates = new BookingDatesPojo("2020-09-09", "2020-09-21");

        BookingPojo expectedData = new BookingPojo("Selim", "Ak", 15000, true, bookingdates);

        //Send the post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{bir}");
        response.prettyPrint();

        //Validation
        //BookingPojo actualData = response.as(BookingPojo.class);
        JsonPath json = response.jsonPath();

        System.out.println(bookingdates.getCheckin());
        System.out.println(json.getString("booking.bookingdates.checkin"));
        assertEquals(expectedData.getFirstname(), json.getString("booking.firstname"));
        assertEquals(expectedData.getLastname(), json.getString("booking.lastname"));
        assertEquals(expectedData.getTotalprice(), json.getInt("booking.totalprice"));
        assertEquals(expectedData.isDepositpaid(), json.getBoolean("booking.depositpaid"));
        assertEquals(bookingdates.getCheckin(), json.getString("booking.bookingdates.checkin"));
        assertEquals(bookingdates.getCheckout(), json.getString("booking.bookingdates.checkout"));

    }

}
