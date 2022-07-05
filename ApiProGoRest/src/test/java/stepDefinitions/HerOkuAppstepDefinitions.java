package stepDefinitions;

import Data.HerOkuAppData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojos.HerOkuAppPojo;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HerOkuAppstepDefinitions {
    Response response;

    @When("user sends a GET request to {string}")
    public void userSendsAGETRequestTo(String endpoint) {
        RestAssured.basePath = endpoint;
        System.out.println("set api endpoint " + endpoint);

        response = RestAssured.given().accept("application/json").
                when().get();
        response.prettyPrint();
    }

    @Then("user validate status code should be {int}")
    public void userValidateStatusCodeShouldBe(int statusCode) {
       int actualStatusCode = response.getStatusCode();
       assertEquals(statusCode,actualStatusCode);

    }

    @And("content type should be JSON")
    public void contentTypeShouldBeJSON() {

        String actualContentType = response.contentType();
        assertEquals("application/json; charset=utf-8",actualContentType);

    }

    @And("status line should be {string}")
    public void statusLineShouldBe(String statusLine) {

        assertEquals(statusLine,response.getStatusLine());
    }


    @And("response body contains {string}")
    public void responseBodyContains(String included) {

        assertTrue(response.asString().contains(included));
    }

    @And("response body does not contain {string}")
    public void responseBodyDoesNotContain(String exluded) {

        assertFalse(response.asString().contains(exluded));
    }

    @And("server is {string}")
    public void serverIs(String server) {

        assertEquals(server, response.getHeader("Server"));
    }

    @Then("user validates booking exist and sees information")
    public void userValidatesBookingExistAndSeesInformation() {

        //Set the expected data
        Map<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("firstname", "Jim");
        expectedData.put("lastname", "Jackson");
        expectedData.put("totalprice", 626);
        expectedData.put("depositpaid", false);

        Map<String, Object> bookingdates = new HashMap<String, Object>();
        expectedData.put("checkin", "2019-02-15");
        expectedData.put("checkout", "2020-06-17");

        //===================================================================================

        response = RestAssured.given().accept("application/json").when().get();
        //1. way

        Map<String,Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>(){});
        Map<String,Object> actualResponse = (Map<String, Object>) deserializedResponse.get("bookingdates");
        System.out.println(deserializedResponse);

        assertEquals("Jim", deserializedResponse.get("firstname"));
        assertEquals("Jackson", deserializedResponse.get("lastname"));
        assertEquals(626, deserializedResponse.get("totalprice"));
        assertEquals(false, deserializedResponse.get("depositpaid"));

        assertEquals("2019-02-15",actualResponse.get("checkin"));
        assertEquals("2020-06-17",actualResponse.get("checkout"));


        //2. way

        JsonPath json = response.jsonPath();;
        assertEquals(expectedData.get("firstname"), json.get("firstname") );
        assertEquals(expectedData.get("lastname"), json.get("lastname") );
        assertEquals(expectedData.get("totalprice"), json.get("totalprice") );
        assertEquals(expectedData.get("depositpaid"), json.get("depositpaid") );

        assertEquals(actualResponse.get("checkin"),json.get("bookingdates.checkin"));
        assertEquals(actualResponse.get("checkout"),json.get("bookingdates.checkout"));







    }
}
