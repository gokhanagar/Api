package postMethods;


import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


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
        Response response = given().spec(spec).contentType(ContentType.JSON).when().post("/{bir}");
        response.prettyPrint();

        //Validation
        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.isDepositpaid(), actualData.isDepositpaid());
        assertEquals(bookingdates.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingdates.getCheckout(), actualData.getBookingdates().getCheckout());


    }


}
