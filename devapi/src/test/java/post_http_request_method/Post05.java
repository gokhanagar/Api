package post_http_request_method;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;


import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import base_urls.HerokuappBaseUrl;

public class Post05 extends HerokuappBaseUrl {

    /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */

    @Test
    public void PostPojo01(){
        //1. Step: Set the Url
        spec.pathParam("first", "booking");

        //2. Step: Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo bookingPojo = new BookingPojo("Ali","Can",999, true,bookingDates);

        //3. Step: Send POST Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion
        BookingPojo actualPojo = response.as(BookingPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(bookingPojo.getFirstname(),actualPojo.getFirstname());
        assertEquals(bookingPojo.getLastname(), actualPojo.getLastname());
        assertEquals(bookingPojo.getTotalprice(), actualPojo.getTotalprice());
        assertEquals(bookingPojo.isDepositpaid(), actualPojo.isDepositpaid());

        //1. Yol
        assertEquals(bookingPojo.getBookingdates().getCheckin(), actualPojo.getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(), actualPojo.getBookingdates().getCheckout());

        //2. Yol
        assertEquals(bookingDates.getCheckin(), actualPojo.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualPojo.getBookingdates().getCheckout());
        assertEquals(bookingPojo.getBooking(), actualPojo.getBooking());
    }
}
